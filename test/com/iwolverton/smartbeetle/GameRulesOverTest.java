package com.iwolverton.smartbeetle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.FireAnt;
import com.iwolverton.smartbeetle.elements.Spider;
import com.iwolverton.smartbeetle.internal.GameRules;

public class GameRulesOverTest {
	
	GameState state = new GameState(
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(17, 4),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beetle(10, 10),
			new Spider(0, 19),
			Arrays.asList(new FireAnt(17, 4))
	);
	
	GameRules rules = new GameRules();
	
	@Test
	void noCharge() {
		state = new GameState(state, new Beetle(19, 5, 0, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void noChargeButCharging() {
		state = new GameState(state, new Beetle(3, 3, 0, 1));
		assertFalse(rules.isGameOver(state));
	}
	
	@Test
	void spiderCollision() {
		state = new GameState(state, new Beetle(0, 19, 50, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void antCollision() {
		state = new GameState(state, new Beetle(17, 4, 50, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void lifeIsGood() {
		state = new GameState(state, new Beetle(18, 4, 1, 0));
		assertFalse(rules.isGameOver(state));
	}

}
