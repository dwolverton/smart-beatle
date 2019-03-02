package com.iwolverton.smartbeatle.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;

public class GameStateFactory {
	
	private static Random rand = new Random();

	public static GameState getTestState1() {
		return new GameState(
				Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
				new AntHill(17, 4),
				Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
				new Beatle(10, 10),
				new Spider(0, 19),
				Arrays.asList(new FireAnt(17, 4))
		);
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
		
		AntHill hill = new AntHill(collisionDetector.randCoordWithoutCollision());
		collisionDetector.add(hill);
		
		List<Bead> beads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Bead bead = new Bead(collisionDetector.randCoordWithoutCollision());
			beads.add(bead);
			collisionDetector.add(bead);
		}
		
		Beatle beatle = new Beatle(collisionDetector.randCoordWithoutCollision());
		collisionDetector.add(beatle);
		
		Spider spider = new Spider(collisionDetector.randCoordWithoutCollision());
		collisionDetector.add(spider);
		
		return new GameState(
				Arrays.asList(p1, p2),
				hill,
				beads,
				beatle,
				spider,
				Collections.emptyList()
				);
	}
	
	public static int randCoordPart() {
		return rand.nextInt(GameState.FIELD_DIMENSION);
	}
	
	public static Coord randCoord() {
		return new Coord(randCoordPart(), randCoordPart());
	}
	

}
