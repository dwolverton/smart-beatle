package com.iwolverton.smartbeatle.elements;

import java.awt.Color;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.internal.DrawingParams;

public class Beatle extends ActingElement {
	
	public static final int DEFAULT_START_CHARGE = 100;
	public static final int DEFAULT_START_AMMO = 1;
	private static final Color COLOR = new Color(0x0066ff);
	
	int charge = DEFAULT_START_CHARGE;
	int ammo = DEFAULT_START_AMMO;

	public Beatle(int x, int y, int charge, int ammo) {
		super(x, y, 1);
		this.charge = charge;
		this.ammo = ammo;
	}
	
	public Beatle(int x, int y) {
		super(x, y, 1);
	}
	
	public Beatle(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
	public Beatle(Coord coord, int charge, int ammo) {
		this(coord.getX(), coord.getY(), charge, ammo);
	}
	
	public Beatle withCoords(int x, int y) {
		return new Beatle(x, y, charge, ammo);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beatle other = (Beatle) obj;
		if (ammo != other.ammo)
			return false;
		if (charge != other.charge)
			return false;
		return true;
	}
	
	

}
