package com.iwolverton.smartbeetle.elements;

import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

public class ChargingPad extends GameElement {
	
	private static final Color COLOR = new Color(0xffff00);

	public ChargingPad(int x, int y) {
		super(x, y);
	}
	
	public ChargingPad(Coord coord) {
		this(coord.getX(), coord.getY());
	}

	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillRect(dp.startX(x), dp.startY(y), dp.cellWidth(), dp.cellHeight());
	}

}
