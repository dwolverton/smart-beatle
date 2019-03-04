package com.iwolverton.smartbeetle.sample;
import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.elements.ChargingPad;

public class ChargeAwayFromSpiderAi2 implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(ChargeAwayFromSpiderAi2.class);
	}
	
	private ChargingPad pad;

	@Override
	public void init(GameState state, Settings settings) {
		pickPad(state);
	}

	@Override
	public Action turn(GameState state) {
		if (pad.rightAngleDistanceFrom(state.getSpider()) <= 3) {
			pickPad(state);
		}
		
		Direction dir = state.getBeetle().directionTo(pad);
		Coord dest = dir.apply(state.getBeetle());
		if (dest.diagonalDistanceFrom(state.getSpider()) < 2) {
			dir = state.getBeetle().altDirectionTo(pad);
		}
		
		return Action.move(dir);
	}
		
	private void pickPad(GameState state) {
		int pad1Dist = state.getChargingPads().get(0).rightAngleDistanceFrom(state.getSpider());
		int pad2Dist = state.getChargingPads().get(1).rightAngleDistanceFrom(state.getSpider());
		
		if (pad1Dist >= pad2Dist) {
			pad = state.getChargingPads().get(0);
		} else {
			pad = state.getChargingPads().get(1);
		}
	}

}
