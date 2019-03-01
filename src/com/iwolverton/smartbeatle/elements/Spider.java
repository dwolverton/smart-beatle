package com.iwolverton.smartbeatle.elements;

import java.awt.Color;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.internal.DrawingParams;

public class Spider extends ActingElement {
	
	public static final int DEFAULT_FREQUENCY = 3;
	private static final Color COLOR = new Color(0x000000);

	public Spider(int x, int y, int nextMove) {
		super(x, y, nextMove);
	}
	
	public Spider(int x, int y) {
		super(x, y, DEFAULT_FREQUENCY);
	}
	
	public Spider(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.startX(x) + 1, dp.startY(y) + 1,
				dp.cellWidth() - 2, dp.cellHeight() - 2);
	}

}
