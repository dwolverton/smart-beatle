package com.iwolverton.smartbeetle.internal;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.elements.Beetle;

public class BeetleBuilder extends Beetle {

	public BeetleBuilder(Beetle from) {
		super(from);
	}
	
	public Beetle build() {
		return new Beetle(this);
	}
	
	public BeetleBuilder setX(int x) {
		this.x = x;
		return this;
	}
	
	public BeetleBuilder setY(int y) {
		this.y = y;
		return this;
	}
	
	public BeetleBuilder setCoord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
		return this;
	}
	
	public BeetleBuilder setCharge(int charge) {
		this.charge = charge;
		return this;
	}
	
	public BeetleBuilder addCharge(int change, int max) {
		this.charge += change;
		if (charge < 0) {
			charge = 0;
		} else if (charge > max) {
			charge = max;
		}
		return this;
	}
	
	public BeetleBuilder setAmmo(int ammo) {
		this.ammo = ammo;
		return this;
	}
	
	public BeetleBuilder addAmmo(int change) {
		this.ammo += change;
		if (ammo < 0) {
			ammo = 0;
		}
		return this;
	}

}
