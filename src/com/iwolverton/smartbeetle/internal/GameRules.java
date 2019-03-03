package com.iwolverton.smartbeetle.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.actions.MoveAction;
import com.iwolverton.smartbeetle.actions.ShootAction;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.FireAnt;
import com.iwolverton.smartbeetle.elements.Spider;

public class GameRules {

	public boolean isGameOver(GameState state) {
		if (state.getBeetle().getCharge() <= 0 && !state.getChargingPads()
				.stream().anyMatch(state.getBeetle()::isAt)) {
			return true;
		}
		if (state.getSpider().isAt(state.getBeetle())) {
			return true;
		}
		for (FireAnt ant : state.getFireAnts()) {
			if (ant.isAt(state.getBeetle())) {
				return true;
			}
		}
		return false;
	}

	public GameState applyAction(GameState state, Action action) {
		BeetleBuilder beetle = new BeetleBuilder(state.getBeetle());
		beetle.addCharge(-1);
		if (action instanceof MoveAction) {
			MoveAction a = (MoveAction) action;
			Coord dest = a.getDirection().apply(beetle);
			ensureInBounds(dest);
			beetle.setCoord(dest);

			return new GameState(state, beetle.build());
		} else if (action instanceof ShootAction && beetle.getAmmo() > 0) {
			beetle.addAmmo(-1);
			Coord target = ((ShootAction) action).getDirection().apply(beetle);
			List<FireAnt> ants = state.getFireAnts().stream()
					.filter(ant -> !ant.isAt(target))
					.collect(Collectors.toList());
			
			return new GameState(state, beetle.build(), ants);
		} else {
			return new GameState(state, beetle.build());
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
		BeetleBuilder beetle = new BeetleBuilder(state.getBeetle());
		
		if (state.getChargingPads().stream().anyMatch(beetle::isAt)) {
			beetle.addCharge(4);
		}
		
		List<Bead> beads = state.getBeads().stream()
				.filter(bead -> !beetle.isAt(bead))
				.collect(Collectors.toList());
		if (beads.size() != state.getBeads().size()) { // we had a hit
			beetle.addAmmo(1);
		}
		
		CollisionDetector collisionDetector = new CollisionDetector();
		collisionDetector.add(state.getFireAnts());

		Spider spider = state.getSpider();
		int nextMove = spider.getNextMove() - 1;
		if (nextMove == 0) {
			Coord dest = moveToward(spider, beetle);
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
					Coord dest = moveToward(ant, beetle);
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
		
		if (beetle.getAmmo() + beads.size() < 4) {
			// place additional bead
			CollisionDetector ammoCD = new CollisionDetector()
					.add(state.getChargingPads())
					.add(beads)
					.add(beetle);
			beads = new ArrayList<>(beads); // ensure mutable
			beads.add(new Bead(ammoCD.randCoordWithoutCollision()));
		}

		return new GameState(state.getChargingPads(), hill, beads,
				beetle.build(), spider, ants);
	}

	private static Coord moveToward(Coord source, Coord target) {
		Direction dir = source.directionTo(target);
		return new Coord(source.getX() + dir.getX(),
				source.getY() + dir.getY());
	}

}
