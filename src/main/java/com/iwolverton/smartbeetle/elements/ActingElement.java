package com.iwolverton.smartbeetle.elements;

import com.iwolverton.smartbeetle.Coord;

/**
 * Superclass for game elements that act. It includes a <code>nextMove</code> property
 * that indicates how many turns until the next action.
 */
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

	/**
	 * This property indicates how many turns until the next action (e.g. spider moves
	 * or new ant from the hill)--one means it will be this turn, immediately after the
	 * beetle's action.
	 */
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
