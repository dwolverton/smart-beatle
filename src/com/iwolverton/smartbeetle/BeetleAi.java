package com.iwolverton.smartbeetle;

import com.iwolverton.smartbeetle.actions.Action;

public interface BeetleAi {
	
	default public void init(GameState state) {}
	
	public Action turn(GameState state);
}
