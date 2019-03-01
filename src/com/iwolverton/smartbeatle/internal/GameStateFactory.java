package com.iwolverton.smartbeatle.internal;

import java.util.Arrays;

import com.iwolverton.smartbeatle.AntHill;
import com.iwolverton.smartbeatle.Bead;
import com.iwolverton.smartbeatle.ChargingPad;
import com.iwolverton.smartbeatle.FireAnt;
import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.Beatle;
import com.iwolverton.smartbeatle.Spider;

public class GameStateFactory {

	public static GameState getTestState1() {
		return new GameState(
				Arrays.asList(new ChargingPad(3, 3), new ChargingPad(16, 17)),
				new AntHill(17, 4),
				Arrays.asList(new Bead(6, 8), new Bead(14, 8), new Bead(10, 15)),
				new Beatle(10, 10),
				new Spider(0, 19),
				Arrays.asList(new FireAnt(17, 4))
		);
	}

}
