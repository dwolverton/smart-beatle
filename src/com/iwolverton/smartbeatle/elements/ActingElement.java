package com.iwolverton.smartbeatle.elements;

public class ActingElement extends GameElement {

	private int nextMove;

	public ActingElement(int x, int y, int nextMove) {
		super(x, y);
		this.nextMove = nextMove;
	}

	public int getNextMove() {
		return nextMove;
	}

}
