package com.iwolverton.smartbeetle;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.Ant;
import com.iwolverton.smartbeetle.elements.Spider;
import com.iwolverton.smartbeetle.internal.GameRules;

public class GameRulesActionTest {
	
	GameState start = new GameState(
			0,
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(new Coord(17, 4), 10, 10),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beetle(new Coord(10, 10), 50, 1),
			new Spider(0, 19, 3),
			Arrays.asList(new Ant(17, 4))
	);
	
	GameRules rules = GameRules.withDefaultSettings();
	
	@Test
	void testMove() {
		GameState end = rules.applyAction(start, Action.move(Direction.E));
		assertEquals(11, end.getBeetle().getX());
		assertEquals(10, end.getBeetle().getY());
		assertEquals(49, end.getBeetle().getCharge());
		assertEquals(1, end.getBeetle().getAmmo());
	}
	
	@Test
	void testMoveOutOfBoundsMaxX() {
		start = new GameState(start, new Beetle(new Coord(19, 5), 50, 1));
		assertThrows(RuntimeException.class, () ->
				rules.applyAction(start, Action.move(Direction.E)));
	}
	
	@Test
	void testMoveOutOfBoundsMinX() {
		start = new GameState(start, new Beetle(new Coord(0, 5), 50, 1));
		assertThrows(RuntimeException.class, () ->
				rules.applyAction(start, Action.move(Direction.W)));
	}
	
	@Test
	void testShoot() {
		start = new GameState(start, new Beetle(17, 5, 50, 1));
		GameState end = rules.applyAction(start, Action.shoot(Direction.N));
		assertEquals(17, end.getBeetle().getX());
		assertEquals(5, end.getBeetle().getY());
		assertEquals(49, end.getBeetle().getCharge());
		assertEquals(0, end.getBeetle().getAmmo());
		assertEquals(0, end.getAnts().size());
	}
	
	@Test
	void testMiss() {
		start = new GameState(start, new Beetle(17, 5, 50, 1));
		GameState end = rules.applyAction(start, Action.shoot(Direction.E));
		assertEquals(17, end.getBeetle().getX());
		assertEquals(5, end.getBeetle().getY());
		assertEquals(49, end.getBeetle().getCharge());
		assertEquals(0, end.getBeetle().getAmmo());
		assertEquals(start.getAnts(), end.getAnts());
	}
	
	@Test
	void testShootNoAmmo() {
		start = new GameState(start, new Beetle(17, 5, 50, 0));
		GameState end = rules.applyAction(start, Action.shoot(Direction.N));
		assertEquals(17, end.getBeetle().getX());
		assertEquals(5, end.getBeetle().getY());
		assertEquals(49, end.getBeetle().getCharge());
		assertEquals(0, end.getBeetle().getAmmo());
		assertEquals(start.getAnts(), end.getAnts());
	}
	
	@Test
	void testStay() {
		GameState end = rules.applyAction(start, Action.stay());
		assertEquals(10, end.getBeetle().getX());
		assertEquals(10, end.getBeetle().getY());
		assertEquals(49, end.getBeetle().getCharge());
		assertEquals(1, end.getBeetle().getAmmo());
	}

}
