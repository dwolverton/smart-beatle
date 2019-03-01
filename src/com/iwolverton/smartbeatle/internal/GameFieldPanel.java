package com.iwolverton.smartbeatle.internal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.elements.GameElement;

public class GameFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameState state;
	private DrawingParams dp;
	private Graphics2D g;

	private static final Color BACKGROUND_COLOR = new Color(0xf9f2ec);
	private static final Color GRID_COLOR = new Color(0x734d26);

	public void setGameState(GameState state) {
		this.state = state;
		this.revalidate();
	}

	@Override
	public void paint(Graphics g1) {
		g = (Graphics2D) g1;
		dp = new DrawingParams(g, getWidth(), getHeight());
		drawBackground();
		drawElements();
		drawGrid();
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
		state.getBeatle().draw(dp);
		state.getSpider().draw(dp);
		drawEach(state.getFireAnts());
	}

	private void drawEach(Iterable<? extends GameElement> elements) {
		for (GameElement el : elements) {
			el.draw(dp);
		}
	}

}
