package com.iwolverton.smartbeatle;

import java.awt.Color;

import com.iwolverton.smartbeatle.internal.DrawingParams;

public class Bead extends GameElement {
	
	private static final Color COLOR = new Color(0x29a329);

	public Bead(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.centerX(x) - dp.cellWidth() / 4, dp.centerY(y) - dp.cellHeight() / 4,
				dp.cellWidth() / 2, dp.cellHeight() / 2);
	}

}
