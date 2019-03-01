package com.iwolverton.smartbeatle.internal;

import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.actions.Action;
import com.iwolverton.smartbeatle.actions.MoveAction;

public class GameRules {

	public static GameState applyAction(GameState state, Action action) {
		if (action instanceof MoveAction) {
			MoveAction a = (MoveAction) action;
			int x = state.getBeatle().getX() + a.getDirection().getX();
			int y = state.getBeatle().getY() + a.getDirection().getY();
			ensureInBounds(x, y);
			return state.withBeatle(state.getBeatle().withCoords(x, y));
		} else {
			return state;
		}
	}
	
	private static void ensureInBounds(int x, int y) {
		if (x < 0 || y < 0 || x >= GameState.FIELD_DIMENSION || y >= GameState.FIELD_DIMENSION) {
			throw new RuntimeException("Out of Bounds: " + x + "," + y);
		}
	}
	
	public static GameState applyRules(GameState state) {
		return state;
	}

}
