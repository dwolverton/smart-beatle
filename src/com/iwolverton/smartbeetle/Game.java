package com.iwolverton.smartbeetle;
import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iwolverton.smartbeetle.actions.Action;
import com.iwolverton.smartbeetle.internal.GameFieldPanel;
import com.iwolverton.smartbeetle.internal.GameRules;
import com.iwolverton.smartbeetle.internal.GameStateFactory;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;

	private GameFieldPanel field = new GameFieldPanel();
	private JLabel stats = new JLabel();
	private JButton scoreAiButton = new JButton("Score AI");
	private JButton runAiButton = new JButton("Run AI");
	private JButton newGameButton = new JButton("New Game");
	
	private GameRules rules = new GameRules();
	private GameState state;
	private boolean error;
	private boolean gameOver;
	private Class<? extends BeetleAi> aiClass;

	public Game(Class<? extends BeetleAi> aiClass) {
		this.aiClass = aiClass;
		
		setTitle("Smart Beetle");
		setSize(540, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		
		newGameButton.addActionListener(e -> startNewGame());
		runAiButton.addActionListener(e -> runAi());

		JPanel bar = new JPanel();
		if (aiClass != null) {
			bar.add(scoreAiButton);
			bar.add(runAiButton);
		}
		bar.add(newGameButton);
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


		startNewGame();

		setVisible(true);
	}
	
	public Game() {
		this(null);
	}
	
	private void startNewGame() {
		// state = GameStateFactory.getTestState1();
		state = new GameStateFactory().getRandomState();
		error = false;
		updateState();
	}

	private void doTurn(Action action) {
		if (!gameOver) {
			state = rules.doTurn(state, action);
			updateState();
		}
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
		gameOver = error || rules.isGameOver(state);
		field.setGameState(state, gameOver);
		stats.setText("Turn: " + state.getTurn() + " Ammo: " + state.getBeetle().getAmmo() + " Charge: "
				+ state.getBeetle().getCharge());
		runAiButton.setEnabled(!gameOver);
	}
	
	private void runAi() {
		try {
			BeetleAi ai = aiClass.newInstance();
			new Thread(() -> {
				try {
					ai.init(state);
					while (!gameOver) {
						doTurn(ai.turn(state));
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
					error = true;
					updateState();
				}
			}).start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
