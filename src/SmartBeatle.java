import javax.swing.JFrame;

import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.internal.GameFieldPanel;
import com.iwolverton.smartbeatle.internal.GameStateFactory;

public class SmartBeatle extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new SmartBeatle();
	}

	public SmartBeatle() {
		setTitle("Smart Beatle");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

//		GameState state = GameStateFactory.getTestState1();
		GameState state = new GameStateFactory().getRandomState();
		GameFieldPanel panel = new GameFieldPanel();
		panel.setGameState(state);

		add(panel);
		setVisible(true);
	}

}
