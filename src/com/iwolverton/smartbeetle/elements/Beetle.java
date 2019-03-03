package com.iwolverton.smartbeetle.elements;

import java.awt.Color;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

public class Beetle extends ActingElement {
	
	public static final int MAX_CHARGE = 100;
	public static final int START_CHARGE = 50;
	public static final int START_AMMO = 1;
	private static final Color COLOR = new Color(0x0066ff);
	
	protected int charge = START_CHARGE;
	protected int ammo = START_AMMO;

	public Beetle(int x, int y, int charge, int ammo) {
		super(x, y, 1);
		this.charge = charge;
		this.ammo = ammo;
	}
	
	public Beetle(int x, int y) {
		super(x, y, 1);
	}
	
	public Beetle(Beetle from) {
		this(from.x, from.y, from.charge, from.ammo);
	}
	
	public Beetle(Coord coord) {
		this(coord.getX(), coord.getY());
	}
	
	public Beetle(Coord coord, int charge, int ammo) {
		this(coord.getX(), coord.getY(), charge, ammo);
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
		Beetle other = (Beetle) obj;
		if (ammo != other.ammo)
			return false;
		if (charge != other.charge)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + "(" + charge + "," + ammo + ")";
	}
	
}
