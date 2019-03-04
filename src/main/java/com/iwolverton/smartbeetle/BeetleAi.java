package com.iwolverton.smartbeetle;

import com.iwolverton.smartbeetle.actions.Action;

/**
 * Implement this interface to build an AI for your beetle.
 * 
 * Your class is also required have a no-args constructor. A new instance of your
 * class will be created for each game and used for the duration of the game.
 */
public interface BeetleAi {
	
	/**
	 * Optionally implement this method. The game will call this method once before
	 * the first turn of each game.
	 */
	default public void init(GameState state, Settings settings) {}
	
	/**
	 * You must implement this method. The game will call this method for every
	 * turn. Your code should use the current game state to determine and return
	 * the appropriate action.
	 */
	public Action turn(GameState state);
}
