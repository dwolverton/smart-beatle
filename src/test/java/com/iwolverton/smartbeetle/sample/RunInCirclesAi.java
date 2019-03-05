package com.iwolverton.smartbeetle.sample;
import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.actions.Action;

public class RunInCirclesAi implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(RunInCirclesAi.class);
	}
	
	private Direction direction;
	private int distance;

	@Override
	public void init(GameState state, Settings settings) {
		// set initial direction toward middle
		direction = state.getBeetle().directionTo(new Coord(10, 10));
		distance = 3;
	}

	@Override
	public Action turn(GameState state) {
		if (--distance == 0) {
			distance = 3;
			direction = direction.nextClockwise();
		}
		return Action.move(direction);
	}

}
