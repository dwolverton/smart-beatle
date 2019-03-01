package com.iwolverton.smartbeatle;

import java.awt.BasicStroke;
import java.awt.Color;

import com.iwolverton.smartbeatle.internal.DrawingParams;

public class AntHill extends ActingElement {
	
	public static final int DEFAULT_FREQUENCY = 15;
	private static final Color COLOR = new Color(0xbf8040);

	public AntHill(int x, int y, int nextMove) {
		super(x, y, nextMove);
	}
	
	public AntHill(int x, int y) {
		super(x, y, DEFAULT_FREQUENCY);
	}

	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.setStroke(new BasicStroke(dp.cellWidth() / 4f + 1));
		dp.g.drawOval(dp.startX(x) + dp.cellWidth() / 4 + 1, dp.startY(y) + dp.cellHeight() / 4 + 1,
				dp.cellWidth() / 2, dp.cellHeight() / 2);
	}

}
