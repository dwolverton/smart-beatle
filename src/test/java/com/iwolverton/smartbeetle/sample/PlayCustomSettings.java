package com.iwolverton.smartbeetle.sample;

import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.SettingsBuilder;

public class PlayCustomSettings {

	public static void main(String[] args) {
		new Game(KillAntsAi.class, new SettingsBuilder()
				.setChargeCostToStay(1)
				.setAntHillStartDelay(20)
				.setAntHillStartFrequency(20)
				.build());
	}
}
