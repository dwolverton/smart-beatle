package com.iwolverton.smartbeatle.internal;

import java.awt.Graphics2D;

import com.iwolverton.smartbeatle.GameState;

public class DrawingParams {

	public final Graphics2D g;
	public final int width, height;

	public DrawingParams(Graphics2D g, int width, int height) {
		this.g = g;
		this.width = width;
		this.height = height;
	}

	public int startX(int x) {
		return width * x / GameState.FIELD_DIMENSION;
	}
	
	public int centerX(int x) {
		return width * (2 * x + 1) / 2 / GameState.FIELD_DIMENSION;
	}

	public int endX(int x) {
		return startX(x + 1) - 1;
	}
	
	public int cellWidth() {
		return width / GameState.FIELD_DIMENSION;
	}

	public int startY(int y) {
		return height * y / GameState.FIELD_DIMENSION;
	}
	
	public int centerY(int y) {
		return height * (2 * y + 1) / 2 / GameState.FIELD_DIMENSION;
	}

	public int endY(int y) {
		return startY(y + 1) - 1;
	}
	
	public int cellHeight() {
		return height / GameState.FIELD_DIMENSION;
	}

}
