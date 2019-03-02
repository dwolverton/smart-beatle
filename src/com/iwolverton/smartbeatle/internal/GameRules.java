package com.iwolverton.smartbeatle.internal;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.Direction;
import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.actions.Action;
import com.iwolverton.smartbeatle.actions.MoveAction;
import com.iwolverton.smartbeatle.actions.ShootAction;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
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
		Beatle beatle = state.getBeatle();
		if (action instanceof MoveAction) {
			MoveAction a = (MoveAction) action;
			Coord dest = a.getDirection().apply(beatle);
			ensureInBounds(dest);

			return new GameState(state,
					new Beatle(dest, beatle.getCharge() - 1, beatle.getAmmo()));
		} else if (action instanceof ShootAction && beatle.getAmmo() > 0) {
			Coord target = ((ShootAction) action).getDirection().apply(beatle);
			List<FireAnt> ants = state.getFireAnts().stream()
					.filter(ant -> !ant.isAt(target))
					.collect(Collectors.toList());
			beatle = new Beatle(beatle, beatle.getCharge() - 1,
					beatle.getAmmo() - 1);
			return new GameState(state, beatle, ants);
		} else {
			return new GameState(state,
					new Beatle(beatle, beatle.getCharge() - 1, beatle.getAmmo()));
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
		Beatle beatle = state.getBeatle();
		Coord beatleCoord = beatle;
		
		if (state.getChargingPads().stream().anyMatch(beatleCoord::isAt)) {
			beatle = new Beatle(beatle, Math.min(beatle.getCharge() + 4, Beatle.DEFAULT_START_CHARGE), beatle.getAmmo());
		}
		
		List<Bead> beads = state.getBeads().stream()
				.filter(bead -> !beatleCoord.isAt(bead))
				.collect(Collectors.toList());
		if (beads.size() != state.getBeads().size()) { // we had a hit
			beatle = new Beatle(beatle, beatle.getCharge(), beatle.getAmmo() + 1);
		}
		
		List<FireAnt> ants = state.getFireAnts().stream()
				.map(ant -> new FireAnt(moveToward(ant, beatleCoord)))
				.collect(Collectors.toList());

		Spider spider = state.getSpider();
		int nextMove = spider.getNextMove() - 1;
		if (nextMove == 0) {
			spider = new Spider(moveToward(spider, beatle));
		} else {
			spider = new Spider(spider, nextMove);
		}

		AntHill hill = state.getAntHill();
		nextMove = hill.getNextMove() - 1;
		if (nextMove == 0) {
			hill = new AntHill(hill);
			ants.add(new FireAnt(hill));
		} else {
			hill = new AntHill(hill, nextMove);
		}
		
		if (beatle.getAmmo() + beads.size() < 4) {
			// place additional bead
		}

		return new GameState(state.getChargingPads(), hill, beads,
				beatle, spider, ants);
	}

	private static Coord moveToward(Coord source, Coord target) {
		Direction dir = source.directionTo(target);
		return new Coord(source.getX() + dir.getX(),
				source.getY() + dir.getY());
	}

}
