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

public class GameRulesTest {
	
	GameState start = new GameState(
			Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
			new AntHill(17, 4),
			Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
			new Beatle(10, 10),
			new Spider(0, 19),
			Arrays.asList(new FireAnt(17, 4))
	);
	
	GameRules rules = new GameRules();
	
	@Test
	void testEnemiesMove() {
		GameState end = rules.applyRules(start);
		assertEquals(start.getBeatle(), end.getBeatle());
		assertEquals(new FireAnt(16, 4), end.getFireAnts().get(0));
		assertEquals(new Spider(0, 19, Spider.DEFAULT_FREQUENCY - 1), end.getSpider());
		assertEquals(new AntHill(17, 4, AntHill.DEFAULT_FREQUENCY - 1, AntHill.DEFAULT_FREQUENCY), end.getAntHill());
	}
	
	@Test
	void testEnemiesMoveWhenFrequencyExpired() {
		start = new GameState(
				Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
				new AntHill(17, 4, 1, AntHill.DEFAULT_FREQUENCY),
				Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
				new Beatle(10, 10),
				new Spider(0, 19, 1),
				Arrays.asList(new FireAnt(15, 4))
		);
		
		GameState end = rules.applyRules(start);
		assertEquals(start.getBeatle(), end.getBeatle());
		assertEquals(new Spider(1, 19, Spider.DEFAULT_FREQUENCY), end.getSpider());
		assertEquals(new AntHill(17, 4, AntHill.DEFAULT_FREQUENCY - 1, AntHill.DEFAULT_FREQUENCY - 1), end.getAntHill());
		assertEquals(2, end.getFireAnts().size());
		assertEquals(new FireAnt(17, 4), end.getFireAnts().get(1));
	}
	
	@Test
	void testCollectAmmo() {
		start = new GameState(start, new Beatle(14, 8, 100, 1));
		GameState end = rules.applyRules(start);
		assertEquals(new Beatle(14, 8, 100, 2), end.getBeatle());
		assertEquals(Arrays.asList(new Bead(6, 8), new Bead(10, 15)), end.getBeads());
	}
	
	// TODO enemies can't overlap

}
