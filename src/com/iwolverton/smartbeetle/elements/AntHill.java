package com.iwolverton.smartbeetle.elements;

import java.awt.BasicStroke;
import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

public class AntHill extends ActingElement {
	
	public static final int START_FREQUENCY = 100;
	private static final Color COLOR = new Color(0xbf8040);
	
	private int frequency = START_FREQUENCY;

	public AntHill(int x, int y, int nextMove, int frequency) {
		super(x, y, nextMove);
		this.frequency = frequency;
	}
	
	public AntHill(int x, int y) {
		super(x, y, START_FREQUENCY);
	}
	
	public AntHill(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
	public AntHill(Coord coord, int nextMove, int frequency) {
		this(coord.getX(), coord.getY(), nextMove, frequency);
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.setStroke(new BasicStroke(dp.cellWidth() / 4f + 1));
		dp.g.drawOval(dp.startX(x) + dp.cellWidth() / 4 + 1, dp.startY(y) + dp.cellHeight() / 4 + 1,
				dp.cellWidth() / 2, dp.cellHeight() / 2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AntHill other = (AntHill) obj;
		if (frequency != other.frequency)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "(" + frequency + ")";
	}

}
