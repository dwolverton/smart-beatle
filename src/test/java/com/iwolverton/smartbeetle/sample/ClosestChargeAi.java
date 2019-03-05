package com.iwolverton.smartbeetle.sample;
import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.elements.ChargingPad;

public class ClosestChargeAi implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(ClosestChargeAi.class);
	}

	@Override
	public Action turn(GameState state) {
		int pad1Dist = state.getChargingPads().get(0).rightAngleDistanceFrom(state.getBeetle());
		int pad2Dist = state.getChargingPads().get(1).rightAngleDistanceFrom(state.getBeetle());
		
		ChargingPad pad;
		if (pad1Dist <= pad2Dist) {
			pad = state.getChargingPads().get(0);
		} else {
			pad = state.getChargingPads().get(1);
		}
		
		Direction directionToPad = state.getBeetle().directionTo(pad);
		
		return Action.move(directionToPad);
	}

}
