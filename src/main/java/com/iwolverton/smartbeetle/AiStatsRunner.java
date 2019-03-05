package com.iwolverton.smartbeetle;

import java.util.ArrayList;
import java.util.List;

import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.internal.GameRules;
import com.iwolverton.smartbeetle.internal.GameStateFactory;

/**
 * Responsible for running AIs non-interactively and reporting statistics.
 */
public class AiStatsRunner {
	
	private static final int GAME_COUNT = 50;

	/**
	 * Run given AI repeatedly and collect survival statistics.
	 */
	public static Stats runAiStats(Class<? extends BeetleAi> aiClass, Settings settings) {
		int best = 0;
		int worst = Integer.MAX_VALUE;
		int total = 0;
		
		for (int i = 0; i < GAME_COUNT; i++) {
			int turn = runAi(aiClass, settings);
			if (turn < worst)
				worst = turn;
			if (turn > best)
				best = turn;
			total += turn;
		}
		
		int average = (int) Math.round((double) total / GAME_COUNT);
		
		return new Stats(aiClass, best, worst, average);
	}

	/**
	 * Run one game with the given AI. Return the number of turns it survived.
	 */
	public static int runAi(Class<? extends BeetleAi> aiClass, Settings settings) {
		GameStateFactory factory = new GameStateFactory(settings);
		GameRules rules = new GameRules(settings);
		GameState state = factory.getRandomState();
		try {
			BeetleAi ai = aiClass.newInstance();
			ai.init(state, settings);
			int MAX_TURNS = 10000;
			while (!rules.isGameOver(state) && state.getTurn() < MAX_TURNS) {
				Action action = ai.turn(state);
				state = rules.doTurn(state, action);
			}
		} catch (Exception ex) {
			if (aiClass != null) {
				System.err.println("The following exception occurred with AI " + aiClass.getName());
			}
			ex.printStackTrace();
		}
		return state.getTurn();
	}
	
	/**
	 * Run given AIs repeatedly and print comparative survival statistics.
	 */
	@SafeVarargs
	public static List<Stats> runAndCompareAis(Settings settings, Class<? extends BeetleAi>... aiClasses) {
		List<Stats> statses = new ArrayList<>(aiClasses.length);
		for (Class<? extends BeetleAi> aiClass : aiClasses) {
			statses.add(runAiStats(aiClass, settings));
		}
		return statses;
	}

}
