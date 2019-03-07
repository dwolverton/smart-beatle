package com.iwolverton.smartbeetle.actions;

import com.iwolverton.smartbeetle.Direction;

/**
 * Return an action instance from your <code>turn</code> method in
 * order determine the beetle's next move.
 */
public interface Action {
	
	/**
	 * Generate an action that will move the beetle one space in the
	 * given direction.
	 */
	public static MoveAction move(Direction direction) {
		return new MoveAction(direction);
	}
	
	/**
	 * Generate an action that will shoot an ant that is one space in the
	 * given direction.
	 */
	public static ShootAction shoot(Direction direction) {
		return new ShootAction(direction);
	}
	
	/**
	 * Generate an action that will cause the beetle to stay in the current
	 * space this turn.
	 */
	public static Action stay() {
		return new MoveAction(Direction.NONE);
	}

}
