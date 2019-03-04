package com.iwolverton.smartbeetle.sample;
import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.actions.Action;

public class DoNothingAi implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(DoNothingAi.class);
	}

	@Override
	public Action turn(GameState state) {
		return Action.stay();
	}

}
