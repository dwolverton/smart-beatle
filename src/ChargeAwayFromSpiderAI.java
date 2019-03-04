import com.iwolverton.smartbeetle.BeetleAi;
import com.iwolverton.smartbeetle.Game;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.Settings;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.elements.ChargingPad;

public class ChargeAwayFromSpiderAI implements BeetleAi {
	
	public static void main(String[] args) {
		new Game(ChargeAwayFromSpiderAI.class);
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
		
		return Action.move(state.getBeetle().directionTo(pad));
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
