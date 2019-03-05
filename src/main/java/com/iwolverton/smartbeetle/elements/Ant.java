package com.iwolverton.smartbeetle.elements;

import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

/**
 * Ants always move toward the beetle. They move one space per turn, just like
 * the beetle. They can be stopped by shooting beads (ammo) at them, but
 * only when they are directly adjacent to the beetle in one of the four
 * cardinal directions.
 * <p>
 * Ants spawn from the ant hill at intervals.
 */
public class Ant extends ActingElement {
	
	public static final int FREQUENCY = 1;
	private static final Color COLOR = new Color(0xff3300);

	public Ant(int x, int y, int nextMove) {
		super(x, y, nextMove);
	}
	
	public Ant(int x, int y) {
		super(x, y, FREQUENCY);
	}
	
	public Ant(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.centerX(x) - dp.cellWidth() / 3, dp.centerY(y) - dp.cellHeight() / 3,
				dp.cellWidth() * 2 / 3, dp.cellHeight() * 2 / 3);
	}

}
