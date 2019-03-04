package com.iwolverton.smartbeetle.actions;

import com.iwolverton.smartbeetle.Direction;

public interface Action {
	
	public static MoveAction move(Direction direction) {
		return new MoveAction(direction);
	}
	
	public static ShootAction shoot(Direction direction) {
		return new ShootAction(direction);
	}
	
	public static Action stay() {
		return new MoveAction(Direction.NONE);
	}

}
