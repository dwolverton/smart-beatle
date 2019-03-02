package com.iwolverton.smartbeatle.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.iwolverton.smartbeatle.Coord;

public class CollisionDetector {

	private List<Coord> coords = new ArrayList<>();
	
	public CollisionDetector add(Coord coord) {
		coords.add(coord);
		return this;
	}
	
	public CollisionDetector add(Collection<? extends Coord> coord) {
		coords.addAll(coord);
		return this;
	}
	
	public CollisionDetector remove(Coord coord) {
		coords.remove(coord);
		return this;
	}
	
	public boolean isCollision(Coord coord) {
		for (Coord other : coords) {
			if (coord.isAt(other)) {
				return true;
			}
		}
		return false;
	}
	
	public Coord randCoordWithoutCollision() {
		Coord c;
		do {
			c = GameStateFactory.randCoord();
		} while (isCollision(c));
		return c;
	}

}
