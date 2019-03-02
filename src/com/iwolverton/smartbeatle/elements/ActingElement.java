package com.iwolverton.smartbeatle.elements;

import com.iwolverton.smartbeatle.Coord;

public abstract class ActingElement extends GameElement {

	private int nextMove;

	public ActingElement(int x, int y, int nextMove) {
		super(x, y);
		this.nextMove = nextMove;
	}
	
	public ActingElement(Coord coord, int nextMove) {
		super(coord);
		this.nextMove = nextMove;
	}

	public int getNextMove() {
		return nextMove;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ActingElement && ((ActingElement) other).getNextMove() != nextMove) {
			return false;
		}
		return super.equals(other);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + x + "," + y + "," + nextMove + ")";
	}

}
