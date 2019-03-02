package com.iwolverton.smartbeatle.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.Direction;
import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.actions.Action;
import com.iwolverton.smartbeatle.actions.MoveAction;
import com.iwolverton.smartbeatle.actions.ShootAction;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;

public class GameRules {

	public boolean isGameOver(GameState state) {
		if (state.getBeatle().getCharge() <= 0 && !state.getChargingPads()
				.stream().anyMatch(state.getBeatle()::isAt)) {
			return true;
		}
		if (state.getSpider().isAt(state.getBeatle())) {
			return true;
		}
		for (FireAnt ant : state.getFireAnts()) {
			if (ant.isAt(state.getBeatle())) {
				return true;
			}
		}
		return false;
	}

	public GameState applyAction(GameState state, Action action) {
		BeatleBuilder beatle = new BeatleBuilder(state.getBeatle());
		beatle.addCharge(-1);
		if (action instanceof MoveAction) {
			MoveAction a = (MoveAction) action;
			Coord dest = a.getDirection().apply(beatle);
			ensureInBounds(dest);
			beatle.setCoord(dest);

			return new GameState(state, beatle.build());
		} else if (action instanceof ShootAction && beatle.getAmmo() > 0) {
			beatle.addAmmo(-1);
			Coord target = ((ShootAction) action).getDirection().apply(beatle);
			List<FireAnt> ants = state.getFireAnts().stream()
					.filter(ant -> !ant.isAt(target))
					.collect(Collectors.toList());
			
			return new GameState(state, beatle.build(), ants);
		} else {
			return new GameState(state, beatle.build());
		}
	}

	private static void ensureInBounds(Coord coord) {
		ensureInBounds(coord.getX(), coord.getY());
	}

	private static void ensureInBounds(int x, int y) {
		if (x < 0 || y < 0 || x >= GameState.FIELD_DIMENSION
				|| y >= GameState.FIELD_DIMENSION) {
			throw new RuntimeException("Out of Bounds: " + x + "," + y);
		}
	}

	public GameState applyRules(GameState state) {
		BeatleBuilder beatle = new BeatleBuilder(state.getBeatle());
		
		if (state.getChargingPads().stream().anyMatch(beatle::isAt)) {
			beatle.addCharge(4);
		}
		
		List<Bead> beads = state.getBeads().stream()
				.filter(bead -> !beatle.isAt(bead))
				.collect(Collectors.toList());
		if (beads.size() != state.getBeads().size()) { // we had a hit
			beatle.addAmmo(1);
		}
		
		CollisionDetector collisionDetector = new CollisionDetector();
		collisionDetector.add(state.getFireAnts());

		Spider spider = state.getSpider();
		int nextMove = spider.getNextMove() - 1;
		if (nextMove == 0) {
			Coord dest = moveToward(spider, beatle);
			if (!collisionDetector.isCollision(dest)) {
				spider = new Spider(dest);
			}
		} else {
			spider = new Spider(spider, nextMove);
		}
		collisionDetector.add(spider);
		
		List<FireAnt> ants = state.getFireAnts().stream()
				.map(ant -> {
					collisionDetector.remove(ant);
					Coord dest = moveToward(ant, beatle);
					if (!collisionDetector.isCollision(dest)) {
						ant = new FireAnt(dest);
					}
					collisionDetector.add(ant);
					return ant;
				})
				.collect(Collectors.toList());

		AntHill hill = state.getAntHill();
		nextMove = hill.getNextMove() - 1;
		if (nextMove == 0) {
			if (!collisionDetector.isCollision(hill)) {
				int nextFrequency = Math.max(hill.getFrequency() - 1, 1);
				hill = new AntHill(hill, nextFrequency, nextFrequency);
				ants.add(new FireAnt(hill));
			}
		} else {
			hill = new AntHill(hill, nextMove, hill.getFrequency());
		}
		
		if (beatle.getAmmo() + beads.size() < 4) {
			// place additional bead
			CollisionDetector ammoCD = new CollisionDetector()
					.add(state.getChargingPads())
					.add(beads)
					.add(beatle);
			beads = new ArrayList<>(beads); // ensure mutable
			beads.add(new Bead(ammoCD.randCoordWithoutCollision()));
		}

		return new GameState(state.getChargingPads(), hill, beads,
				beatle.build(), spider, ants);
	}

	private static Coord moveToward(Coord source, Coord target) {
		Direction dir = source.directionTo(target);
		return new Coord(source.getX() + dir.getX(),
				source.getY() + dir.getY());
	}

}
