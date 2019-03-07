package com.iwolverton.smartbeetle.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.actions.MoveAction;
import com.iwolverton.smartbeetle.actions.ShootAction;
import com.iwolverton.smartbeetle.elements.Ant;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Spider;

public class GameRules {

	private Settings settings;

	public GameRules(Settings settings) {
		this.settings = settings;
	}

	public static GameRules withDefaultSettings() {
		return new GameRules(new Settings());
	}

	public GameState doTurn(GameState state, Action action) {
		state = applyAction(state, action);
		if (!isGameOver(state)) {
			state = applyRules(state);
		}
		return state;
	}

	public boolean isGameOver(GameState state) {
		return determineCauseOfDeath(state) != null;
	}
	
	public String determineCauseOfDeath(GameState state) {
		if (state.getBeetle().getCharge() <= 0 && !state.getChargingPads()
				.stream().anyMatch(state.getBeetle()::isAt)) {
			return "Charge";
		}
		if (state.getSpider().isAt(state.getBeetle())) {
			return "Spider";
		}
		for (Ant ant : state.getAnts()) {
			if (ant.isAt(state.getBeetle())) {
				return "Ant";
			}
		}
		return null;
	}

	public GameState applyAction(GameState state, Action action) {
		if (action instanceof MoveAction && ((MoveAction) action).getDirection() == Direction.NONE) {
			// normalize the stay action
			action = null;
		}
		
		BeetleBuilder beetle = new BeetleBuilder(state.getBeetle());
		if (action instanceof MoveAction) {
			MoveAction a = (MoveAction) action;
			Coord dest = a.getDirection().relativeTo(beetle);
			ensureInBounds(dest);
			beetle.setCoord(dest);
			beetle.addCharge(-settings.getChargeCostToMove(), settings.getBeetleMaxCharge());

			return new GameState(state, beetle.build());
		} else if (action instanceof ShootAction && beetle.getAmmo() > 0) {
			beetle.addAmmo(-1);
			Coord target = ((ShootAction) action).getDirection().relativeTo(beetle);
			List<Ant> ants = state.getAnts().stream()
					.filter(ant -> !ant.isAt(target))
					.collect(Collectors.toList());
			beetle.addCharge(-settings.getChargeCostToShoot(), settings.getBeetleMaxCharge());

			return new GameState(state, beetle.build(), ants);
		} else {
			beetle.addCharge(-settings.getChargeCostToStay(), settings.getBeetleMaxCharge());
			return new GameState(state, beetle.build());
		}
	}

	private static void ensureInBounds(Coord coord) {
		if (coord.isOutOfBounds()) {
			throw new RuntimeException("Out of Bounds: " + coord.getX() + "," + coord.getY());
		}
	}

	public GameState applyRules(GameState state) {
		BeetleBuilder beetle = new BeetleBuilder(state.getBeetle());

		if (state.getChargingPads().stream().anyMatch(beetle::isAt)) {
			beetle.addCharge(settings.getRecharge(), settings.getBeetleMaxCharge());
		}

		List<Bead> beads = state.getBeads().stream()
				.filter(bead -> !beetle.isAt(bead))
				.collect(Collectors.toList());
		if (beads.size() != state.getBeads().size()) { // we had a hit
			beetle.addAmmo(1);
		}

		CollisionDetector collisionDetector = new CollisionDetector();
		collisionDetector.add(state.getAnts());

		Spider spider = state.getSpider();
		int nextMove = spider.getNextMove() - 1;
		if (nextMove == 0) {
			Coord dest = moveToward(spider, beetle);
			if (!collisionDetector.isCollision(dest)) {
				spider = new Spider(dest, settings.getSpiderFrequency());
			}
		} else {
			spider = new Spider(spider, nextMove);
		}
		collisionDetector.add(spider);

		List<Ant> ants = state.getAnts().stream().map(ant -> {
			collisionDetector.remove(ant);
			Coord dest = moveToward(ant, beetle);
			if (!collisionDetector.isCollision(dest)) {
				ant = new Ant(dest);
			}
			collisionDetector.add(ant);
			return ant;
		}).collect(Collectors.toList());

		AntHill hill = state.getAntHill();
		nextMove = hill.getNextMove() - 1;
		if (nextMove == 0) {
			if (!collisionDetector.isCollision(hill)) {
				int nextFrequency = Math.max((int) (hill.getFrequency()
						* settings.getAntHillFrequencyModifier()), 1);
				hill = new AntHill(hill, nextFrequency, nextFrequency);
				ants.add(new Ant(hill));
			}
		} else {
			hill = new AntHill(hill, nextMove, hill.getFrequency());
		}

		if (beetle.getAmmo() + beads.size() < settings.getTotalAmmo()) {
			// place additional bead
			CollisionDetector ammoCD = new CollisionDetector()
					.add(state.getChargingPads()).add(beads).add(beetle);
			beads = new ArrayList<>(beads); // ensure mutable
			beads.add(new Bead(ammoCD.randCoordWithoutCollision()));
		}

		return new GameState(state.getTurn() + 1, state.getChargingPads(), hill,
				beads, beetle.build(), spider, ants);
	}

	private static Coord moveToward(Coord source, Coord target) {
		Direction dir = source.directionTo(target);
		return new Coord(source.getX() + dir.getX(),
				source.getY() + dir.getY());
	}

}
