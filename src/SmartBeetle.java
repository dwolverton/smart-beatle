import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iwolverton.smartbeetle.Direction;
import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.internal.GameFieldPanel;
import com.iwolverton.smartbeetle.internal.GameRules;
import com.iwolverton.smartbeetle.internal.GameStateFactory;

public class SmartBeetle extends JFrame {

	private static final long serialVersionUID = 1L;

	GameFieldPanel field = new GameFieldPanel();
	JLabel stats = new JLabel();
	GameRules rules = new GameRules();
	GameState state;

	public static void main(String[] args) {
		new SmartBeetle();
	}

	public SmartBeetle() {
		setTitle("Smart Beetle");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);

		// state = GameStateFactory.getTestState1();
		state = new GameStateFactory().getRandomState();
		updateState();

		JPanel bar = new JPanel();
		bar.add(new JButton("Score AI"));
		bar.add(new JButton("Run AI"));
		bar.add(stats);
		add(bar);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(bar, BorderLayout.PAGE_START);
		panel.add(field);

		add(panel);

		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyEventDispatcher() {
					@Override
					public boolean dispatchKeyEvent(KeyEvent e) {
						if (e.getID() == KeyEvent.KEY_PRESSED) {
							if (e.getKeyCode() == KeyEvent.VK_SPACE) {
								doTurn(Action.stay());
								return true;
							}
							if (e.isShiftDown()) {
								findDirectionFromKey(e).ifPresent(
										dir -> doTurn(Action.shoot(dir)));
							} else {
								findDirectionFromKey(e).ifPresent(
										dir -> doTurn(Action.move(dir)));
							}
						}
						return false;
					}
				});

		setVisible(true);
	}

	public void doTurn(Action action) {
		state = rules.applyAction(state, action);
		if (!rules.isGameOver(state)) {
			state = rules.applyRules(state);
		}
		updateState();
	}

	private static Optional<Direction> findDirectionFromKey(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			return Optional.of(Direction.N);
		case KeyEvent.VK_RIGHT:
			return Optional.of(Direction.E);
		case KeyEvent.VK_DOWN:
			return Optional.of(Direction.S);
		case KeyEvent.VK_LEFT:
			return Optional.of(Direction.W);
		default:
			return Optional.empty();
		}
	}

	private void updateState() {
		field.setGameState(state);
		stats.setText("Ammo: " + state.getBeetle().getAmmo() + " Charge: "
				+ state.getBeetle().getCharge());
	}

}
