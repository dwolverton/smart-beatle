package com.iwolverton.smartbeetle.actions;

import com.iwolverton.smartbeetle.Direction;

/**
 * An action that will shoot an ant that is one space in the
 * given direction.
 */
public class ShootAction implements Action {

	private Direction direction;

	public ShootAction(Direction direction) {
		if (direction == null) {
			direction = Direction.NONE;
		}
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
