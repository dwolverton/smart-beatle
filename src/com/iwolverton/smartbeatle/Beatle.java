package com.iwolverton.smartbeatle;

import java.awt.Color;

import com.iwolverton.smartbeatle.internal.DrawingParams;

public class Beatle extends ActingElement {
	
	public static final int DEFAULT_START_CHARGE = 100;
	public static final int DEFAULT_START_AMMO = 1;
	private static final Color COLOR = new Color(0x0066ff);
	int charge, ammo;

	public Beatle(int x, int y, int charge, int ammo) {
		super(x, y, 1);
		this.charge = charge;
		this.ammo = ammo;
	}
	
	public Beatle(int x, int y) {
		super(x, y, 1);
	}
	
	public int getCharge() {
		return charge;
	}

	public int getAmmo() {
		return ammo;
	}

	@Override
	public void draw(DrawingParams dp) {
		dp.g.setColor(COLOR);
		dp.g.fillOval(dp.centerX(x) - dp.cellWidth() / 3, dp.centerY(y) - dp.cellHeight() / 3,
				dp.cellWidth() * 2 / 3, dp.cellHeight() * 2 / 3);
	}

}
