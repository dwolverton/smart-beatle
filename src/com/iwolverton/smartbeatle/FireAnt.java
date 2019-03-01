package com.iwolverton.smartbeatle;

import java.awt.Color;

import com.iwolverton.smartbeatle.internal.DrawingParams;

public class FireAnt extends ActingElement {
	
	public static final int DEFAULT_FREQUENCY = 1;
	private static final Color COLOR = new Color(0xff3300);

	public FireAnt(int x, int y, int nextMove) {
		super(x, y, nextMove);
	}
	
	public FireAnt(int x, int y) {
		super(x, y, DEFAULT_FREQUENCY);
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.centerX(x) - dp.cellWidth() / 3, dp.centerY(y) - dp.cellHeight() / 3,
				dp.cellWidth() * 2 / 3, dp.cellHeight() * 2 / 3);
	}

}
