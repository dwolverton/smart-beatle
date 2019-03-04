package com.iwolverton.smartbeetle.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.elements.Ant;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.Spider;

public class GameStateFactory {

	private static Random rand = new Random();
	private Settings settings;

	public GameStateFactory(Settings settings) {
		this.settings = settings;
	}

	public static GameState getTestState1() {
		Settings settings = new Settings();
		return new GameState(0,
				Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
				new AntHill(new Coord(17, 4), settings.getAntHillStartDelay(),
						settings.getAntHillStartFrequency()),
				Arrays.asList(new Bead(6, 8), new Bead(14, 8),
						new Bead(10, 15)),
				new Beetle(new Coord(10, 10), settings.getBeetleStartCharge(), settings.getBeetleStartAmmo()),
				new Spider(0, 19, settings.getSpiderFrequency()),
				Arrays.asList(new Ant(17, 4)));
	}

	public GameState getRandomState() {
		CollisionDetector collisionDetector = new CollisionDetector();
		ChargingPad p1 = new ChargingPad(randCoord());
		ChargingPad p2;
		do {
			p2 = new ChargingPad(randCoord());
		} while (p2.diagonalDistanceFrom(p1) < 5);
		collisionDetector.add(p1);
		collisionDetector.add(p2);

		AntHill hill = new AntHill(
				collisionDetector.randCoordWithoutCollision(),
				settings.getAntHillStartDelay(),
				settings.getAntHillStartFrequency());
		collisionDetector.add(hill);

		List<Bead> beads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Bead bead = new Bead(collisionDetector.randCoordWithoutCollision());
			beads.add(bead);
			collisionDetector.add(bead);
		}

		Beetle beetle = new Beetle(
				collisionDetector.randCoordWithoutCollision(), settings.getBeetleStartCharge(), settings.getBeetleStartAmmo());
		collisionDetector.add(beetle);

		Spider spider = new Spider(
				collisionDetector.randCoordWithoutCollision(),
				settings.getSpiderFrequency());
		collisionDetector.add(spider);

		return new GameState(0, Arrays.asList(p1, p2), hill, beads, beetle,
				spider, Collections.emptyList());
	}

	public static int randCoordPart() {
		return rand.nextInt(GameState.FIELD_DIMENSION);
	}

	public static Coord randCoord() {
		return new Coord(randCoordPart(), randCoordPart());
	}

}
