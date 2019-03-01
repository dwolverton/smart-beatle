package com.iwolverton.smartbeatle;

import com.iwolverton.smartbeatle.actions.Action;

public interface BeatleAI {
	
	public Action turn(GameState state);
}
