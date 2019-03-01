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
		List<Coord> placed = new ArrayList<>();
		ChargingPad p1 = new ChargingPad(randCoord(), randCoord());
		ChargingPad p2;
		do {
			p2 = new ChargingPad(randCoord(), randCoord());
		} while (p2.diagonalDistanceFrom(p1) < 5);
		placed.add(p1);
		placed.add(p2);
		
		AntHill hill = new AntHill(randCoordWithoutCollision(placed));
		placed.add(hill);
		
		List<Bead> beads = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Bead bead = new Bead(randCoordWithoutCollision(placed));
			beads.add(bead);
			placed.add(bead);
		}
		
		Beatle beatle = new Beatle(randCoordWithoutCollision(placed));
		placed.add(beatle);
		
		Spider spider = new Spider(randCoordWithoutCollision(placed));
		placed.add(spider);
		
		return new GameState(
				Arrays.asList(p1, p2),
				hill,
				beads,
				beatle,
				spider,
				Collections.emptyList()
				);
	}
	
	private static int randCoord() {
		return rand.nextInt(GameState.FIELD_DIMENSION);
	}
	
	private static Coord randCoordWithoutCollision(Iterable<Coord> others) {
		Coord c;
		newCoord: do {
			c = new Coord(randCoord(), randCoord());
			for (Coord other : others) {
				if (c.equals(other)) {
					continue newCoord;
				}
			}
		} while (false);
		return c;
	}
	

}
