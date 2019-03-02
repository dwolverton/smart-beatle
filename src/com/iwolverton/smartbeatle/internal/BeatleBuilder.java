package com.iwolverton.smartbeatle.internal;

import com.iwolverton.smartbeatle.Coord;
import com.iwolverton.smartbeatle.elements.Beatle;

public class BeatleBuilder extends Beatle {

	public BeatleBuilder(Beatle from) {
		super(from);
	}
	
	public Beatle build() {
		return new Beatle(this);
	}
	
	public BeatleBuilder setX(int x) {
		this.x = x;
		return this;
	}
	
	public BeatleBuilder setY(int y) {
		this.y = y;
		return this;
	}
	
	public BeatleBuilder setCoord(Coord coord) {
		this.x = coord.getX();
		this.y = coord.getY();
		return this;
	}
	
	public BeatleBuilder setCharge(int charge) {
		this.charge = charge;
		return this;
	}
	
	public BeatleBuilder addCharge(int change) {
		this.charge += change;
		if (charge < 0) {
			charge = 0;
		} else if (charge > MAX_CHARGE) {
			charge = MAX_CHARGE;
		}
		return this;
	}
	
	public BeatleBuilder setAmmo(int ammo) {
		this.ammo = ammo;
		return this;
	}
	
	public BeatleBuilder addAmmo(int change) {
		this.ammo += change;
		if (ammo < 0) {
			ammo = 0;
		}
		return this;
	}

}
