package com.iwolverton.smartbeetle;

public enum Direction {

	N(0, -1), E(1, 0), S(0, 1), W(-1, 0), X(0, 0);

	private int x, y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Coord apply(Coord coord) {
		return new Coord(coord.getX() + x, coord.getY() + y);
	}

}
