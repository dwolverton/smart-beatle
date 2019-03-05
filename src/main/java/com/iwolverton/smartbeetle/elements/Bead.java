package com.iwolverton.smartbeetle.elements;

import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

/**
 * The beetle can collect beads to be used as ammunition against ants.
 * <p>
 * There is a constant total number of beads in the game. (the beetle's ammo +
 * the beads on the field)
 */
public class Bead extends GameElement {
	
	private static final Color COLOR = new Color(0x29a329);

	public Bead(int x, int y) {
		super(x, y);
	}
	
	public Bead(Coord coord) {
		super(coord);
	}
	
	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.centerX(x) - dp.cellWidth() / 4, dp.centerY(y) - dp.cellHeight() / 4,
				dp.cellWidth() / 2, dp.cellHeight() / 2);
	}

}
