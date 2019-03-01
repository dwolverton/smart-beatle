package com.iwolverton.smartbeatle.actions;

import com.iwolverton.smartbeatle.Direction;

public class ShootAction implements Action {

	private Direction direction;

	public ShootAction(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
