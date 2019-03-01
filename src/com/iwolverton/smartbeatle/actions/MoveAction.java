package com.iwolverton.smartbeatle.actions;

import com.iwolverton.smartbeatle.Direction;

public class MoveAction implements Action {

	private Direction direction;

	public MoveAction(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
