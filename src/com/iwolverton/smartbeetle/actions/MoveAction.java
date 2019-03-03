package com.iwolverton.smartbeetle.actions;

import com.iwolverton.smartbeetle.Direction;

public class MoveAction implements Action {

	private Direction direction;

	public MoveAction(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
