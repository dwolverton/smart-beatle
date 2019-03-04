package com.iwolverton.smartbeetle.internal;

import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.Stats;
import com.iwolverton.smartbeetle.actions.Action;

public class AiStatsRunner {
	
	private static final int GAME_COUNT = 100;

	public static Stats runAiStats(Class<? extends BeetleAi> aiClass, Settings settings) {
		GameStateFactory factory = new GameStateFactory(settings);
		GameRules rules = new GameRules(settings);
		
		int best = 0;
		int worst = Integer.MAX_VALUE;
		int total = 0;
		
		for (int i = 0; i < GAME_COUNT; i++) {
			int turn = runAi(aiClass, rules, factory);
			if (turn < worst)
				worst = turn;
			if (turn > best)
				best = turn;
			total += turn;
		}
		
		int average = (int) Math.round((double) total / GAME_COUNT);
		
		return new Stats(best, worst, average);
	}

	public static int runAi(Class<? extends BeetleAi> aiClass, GameRules rules, GameStateFactory factory) {
		GameState state = factory.getRandomState();
		try {
			BeetleAi ai = aiClass.newInstance();
			ai.init(state);
			int MAX_TURNS = 10000;
			while (!rules.isGameOver(state) && state.getTurn() < MAX_TURNS) {
				Action action = ai.turn(state);
				state = rules.doTurn(state, action);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return state.getTurn();
	}

}
