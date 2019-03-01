package com.iwolverton.smartbeatle;

import java.awt.Color;

import com.iwolverton.smartbeatle.internal.DrawingParams;

public class ChargingPad extends GameElement {
	
	private static final Color COLOR = new Color(0xffff00);

	public ChargingPad(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillRect(dp.startX(x), dp.startY(y), dp.cellWidth(), dp.cellHeight());
	}

}
