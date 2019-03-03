package com.iwolverton.smartbeetle;

import com.iwolverton.smartbeetle.actions.Action;

public interface BeetleAI {
	
	public Action turn(GameState state);
}
