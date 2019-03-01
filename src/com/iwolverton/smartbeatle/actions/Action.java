package com.iwolverton.smartbeatle.actions;

import com.iwolverton.smartbeatle.Direction;

public interface Action {
	
	public static MoveAction move(Direction direction) {
		return new MoveAction(direction);
	}
	
	public static ShootAction shoot(Direction direction) {
		return new ShootAction(direction);
	}
	
	public static Action stay() {
		return null;
	}

}
