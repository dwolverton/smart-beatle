package com.iwolverton.smartbeetle.internal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.elements.GameElement;

public class GameFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameState state;
	private boolean gameOver;
	private DrawingParams dp;
	private Graphics2D g;

	private static final Color BACKGROUND_COLOR = new Color(0xf9f2ec);
	private static final Color GRID_COLOR = new Color(0x734d26);
	private static final Color GAME_OVER_COLOR = new Color(0xff33cc);

	public void setGameState(GameState state, boolean gameOver) {
		this.state = state;
		this.gameOver = gameOver;
		this.repaint();
	}

	@Override
	public void paint(Graphics g1) {
		g = (Graphics2D) g1;
		dp = new DrawingParams(g, getWidth(), getHeight());
		drawBackground();
		drawElements();
		drawGrid();
		if (gameOver) {
			drawGameOver();
		}
	}

	private void drawBackground() {
		g.setBackground(BACKGROUND_COLOR);
		g.clearRect(0, 0, dp.width, dp.height);
	}

	private void drawGrid() {
		g.setColor(GRID_COLOR);
		g.setStroke(new BasicStroke(2f));
		for (int i = 0; i <= GameState.FIELD_DIMENSION; i++) {
			int x = dp.startX(i);
			int y = dp.startY(i);
			g.drawLine(x, 0, x, this.getHeight());
			g.drawLine(0, y, this.getWidth(), y);
		}
	}

	private void drawElements() {
		drawEach(state.getChargingPads());
		state.getAntHill().draw(dp);
		drawEach(state.getBeads());
		state.getBeetle().draw(dp);
		state.getSpider().draw(dp);
		drawEach(state.getAnts());
	}

	private void drawEach(Iterable<? extends GameElement> elements) {
		for (GameElement el : elements) {
			el.draw(dp);
		}
	}
	
	private void drawGameOver() {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, getWidth() / 8);
		g.setFont(font);
		g.setColor(GAME_OVER_COLOR);
		
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = (getWidth() - metrics.stringWidth("GAME OVER")) / 2;
	    
	    g.drawString("GAME OVER", x, getHeight() * 2 / 5);
		
	}

}
