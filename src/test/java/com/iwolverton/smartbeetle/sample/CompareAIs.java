package com.iwolverton.smartbeetle.sample;
import com.iwolverton.smartbeetle.AiStatsRunner;
import com.iwolverton.smartbeetle.Settings;

public class CompareAIs {

	public static void main(String[] args) {
		AiStatsRunner.runAndCompareAis(new Settings(),
				DoNothingAi.class,
				ClosestChargeAi.class,
				ChargeAwayFromSpiderAi.class,
				ChargeAwayFromSpiderAi2.class,
				KillAntsAi.class
		).forEach(System.out::println);
	}

}
