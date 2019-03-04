package com.iwolverton.smartbeetle.elements;

import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

public class Spider extends ActingElement {
	
	private static final Color COLOR = new Color(0x000000);

	public Spider(int x, int y, int nextMove) {
		super(x, y, nextMove);
	}
	
	public Spider(Coord coord, int nextMove) {
		this(coord.getX(), coord.getY(), nextMove);
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.startX(x) + 1, dp.startY(y) + 1,
				dp.cellWidth() - 2, dp.cellHeight() - 2);
	}

}
