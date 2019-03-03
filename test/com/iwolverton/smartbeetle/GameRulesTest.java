package com.iwolverton.smartbeetle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.FireAnt;
import com.iwolverton.smartbeetle.elements.Spider;
import com.iwolverton.smartbeetle.internal.GameRules;
import com.iwolverton.smartbeetle.internal.GameStateBuilder;

public class GameRulesTest {
	
	GameStateBuilder start = new GameStateBuilder(
			0,
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(17, 4),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beetle(10, 10),
			new Spider(0, 19),
			Arrays.asList(new FireAnt(17, 4))
	);
	
	GameRules rules = new GameRules();
	
	@Test
	void testEnemiesMove() {
		GameState end = rules.applyRules(start);
		assertEquals(start.getBeetle(), end.getBeetle());
		assertEquals(new FireAnt(16, 4), end.getFireAnts().get(0));
		assertEquals(new Spider(0, 19, Spider.DEFAULT_FREQUENCY - 1), end.getSpider());
		assertEquals(new AntHill(17, 4, AntHill.START_FREQUENCY - 1, AntHill.START_FREQUENCY), end.getAntHill());
	}
	
	@Test
	void testEnemiesMoveWhenFrequencyExpired() {
		start.setAntHill(new AntHill(17, 4, 1, AntHill.START_FREQUENCY))
		.setSpider(new Spider(0, 19, 1))
		.setFireAnts(new FireAnt(15, 4));
		
		GameState end = rules.applyRules(start);
		assertEquals(start.getBeetle(), end.getBeetle());
		assertEquals(new Spider(1, 19, Spider.DEFAULT_FREQUENCY), end.getSpider());
		assertEquals(new AntHill(17, 4, AntHill.START_FREQUENCY - 1, AntHill.START_FREQUENCY - 1), end.getAntHill());
		assertEquals(2, end.getFireAnts().size());
		assertEquals(new FireAnt(17, 4), end.getFireAnts().get(1));
	}
	
	@Test
	void testCollectAmmo() {
		start.setBeetle(new Beetle(14, 8, 100, 1));
		GameState end = rules.applyRules(start);
		assertEquals(new Beetle(14, 8, 100, 2), end.getBeetle());
		assertEquals(Arrays.asList(new Bead(6, 8), new Bead(10, 15)), end.getBeads());
	}
	
	// TODO enemies can't overlap

}
