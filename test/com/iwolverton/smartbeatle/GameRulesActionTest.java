package com.iwolverton.smartbeatle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.iwolverton.smartbeatle.actions.Action;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;
import com.iwolverton.smartbeatle.internal.GameRules;

public class GameRulesActionTest {
	
	GameState start = new GameState(
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(17, 4),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beatle(10, 10, 50, 1),
			new Spider(0, 19),
			Arrays.asList(new FireAnt(17, 4))
	);
	
	GameRules rules = new GameRules();
	
	@Test
	void testMove() {
		GameState end = rules.applyAction(start, Action.move(Direction.E));
		assertEquals(11, end.getBeatle().getX());
		assertEquals(10, end.getBeatle().getY());
		assertEquals(49, end.getBeatle().getCharge());
		assertEquals(1, end.getBeatle().getAmmo());
	}
	
	@Test
	void testMoveOutOfBoundsMaxX() {
		start = new GameState(start, new Beatle(19, 5));
		assertThrows(RuntimeException.class, () ->
				rules.applyAction(start, Action.move(Direction.E)));
	}
	
	@Test
	void testMoveOutOfBoundsMinX() {
		start = new GameState(start, new Beatle(0, 5));
		assertThrows(RuntimeException.class, () ->
				rules.applyAction(start, Action.move(Direction.W)));
	}
	
	@Test
	void testShoot() {
		start = new GameState(start, new Beatle(17, 5, 50, 1));
		GameState end = rules.applyAction(start, Action.shoot(Direction.N));
		assertEquals(17, end.getBeatle().getX());
		assertEquals(5, end.getBeatle().getY());
		assertEquals(49, end.getBeatle().getCharge());
		assertEquals(0, end.getBeatle().getAmmo());
		assertEquals(0, end.getFireAnts().size());
	}
	
	@Test
	void testMiss() {
		start = new GameState(start, new Beatle(17, 5, 50, 1));
		GameState end = rules.applyAction(start, Action.shoot(Direction.E));
		assertEquals(17, end.getBeatle().getX());
		assertEquals(5, end.getBeatle().getY());
		assertEquals(49, end.getBeatle().getCharge());
		assertEquals(0, end.getBeatle().getAmmo());
		assertEquals(start.getFireAnts(), end.getFireAnts());
	}
	
	@Test
	void testShootNoAmmo() {
		start = new GameState(start, new Beatle(17, 5, 50, 0));
		GameState end = rules.applyAction(start, Action.shoot(Direction.N));
		assertEquals(17, end.getBeatle().getX());
		assertEquals(5, end.getBeatle().getY());
		assertEquals(49, end.getBeatle().getCharge());
		assertEquals(0, end.getBeatle().getAmmo());
		assertEquals(start.getFireAnts(), end.getFireAnts());
	}
	
	@Test
	void testStay() {
		GameState end = rules.applyAction(start, Action.stay());
		assertEquals(10, end.getBeatle().getX());
		assertEquals(10, end.getBeatle().getY());
		assertEquals(49, end.getBeatle().getCharge());
		assertEquals(1, end.getBeatle().getAmmo());
	}

}
