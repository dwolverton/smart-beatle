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
    private int count;

    @Override
    public void init(GameState state, Settings settings) {
        // set initial direction toward middle
        direction = state.getBeetle().directionTo(new Coord(10, 10));
        count = 0;
    }

    @Override
    public Action turn(GameState state) {
        if (count++ == 3) {
            count = 1;
            direction = direction.nextClockwise();
        }
        return Action.move(direction);
    }

}
