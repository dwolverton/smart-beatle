package com.iwolverton.smartbeetle.elements;

import com.iwolverton.smartbeetle.Coord;
import com.iwolverton.smartbeetle.internal.DrawingParams;

public abstract class GameElement extends Coord {

	public GameElement(int x, int y) {
		super(x, y);
	}
	
	public GameElement(Coord coord) {
		super(coord);
	}
	
	public abstract void draw(DrawingParams dp);
	
	@Override
	public boolean equals(Object other) {
		if (!this.getClass().equals(other.getClass())) {
			return false;
		}
		return super.equals(other);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + x + "," + y + ")";
	}

}
