import com.iwolverton.smartbeetle.AiStatsRunner;
import com.iwolverton.smartbeetle.Settings;

public class CompareAIs {

	public static void main(String[] args) {
		AiStatsRunner.runAndCompareAis(new Settings(),
				ClosestChargeAi.class,
				ChargeAwayFromSpiderAI.class,
				ChargeAwayFromSpiderAI2.class,
				KillAntsAI.class
		);
	}

}
