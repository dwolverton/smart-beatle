package com.iwolverton.smartbeatle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;
import com.iwolverton.smartbeatle.internal.GameRules;

public class GameRulesOverTest {
	
	GameState state = new GameState(
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(17, 4),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beatle(10, 10),
			new Spider(0, 19),
			Arrays.asList(new FireAnt(17, 4))
	);
	
	GameRules rules = new GameRules();
	
	@Test
	void noCharge() {
		state = new GameState(state, new Beatle(19, 5, 0, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void noChargeButCharging() {
		state = new GameState(state, new Beatle(3, 3, 0, 1));
		assertFalse(rules.isGameOver(state));
	}
	
	@Test
	void spiderCollision() {
		state = new GameState(state, new Beatle(0, 19, 50, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void antCollision() {
		state = new GameState(state, new Beatle(17, 4, 50, 1));
		assertTrue(rules.isGameOver(state));
	}
	
	@Test
	void lifeIsGood() {
		state = new GameState(state, new Beatle(18, 4, 1, 0));
		assertFalse(rules.isGameOver(state));
	}

}
