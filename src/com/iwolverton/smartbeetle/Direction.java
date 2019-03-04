package com.iwolverton.smartbeetle;

public enum Direction {

	N(0, -1, Axis.Y), E(1, 0, Axis.X), S(0, 1, Axis.Y), W(-1, 0,
			Axis.X), NONE(0, 0, Axis.NONE);

	private int x, y;
	private Axis axis;

	private Direction(int x, int y, Axis axis) {
		this.x = x;
		this.y = y;
		this.axis = axis;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Axis getAxis() {
		return axis;
	}

	public Coord apply(Coord coord) {
		return new Coord(coord.getX() + x, coord.getY() + y);
	}

	public Direction opposite() {
		switch (this) {
		case N:
			return S;
		case E:
			return W;
		case S:
			return N;
		case W:
			return E;
		default:
			return NONE;
		}
	}

	public static Direction forAxis(Axis axis, int sign) {
		if (sign == 0) {
			return NONE;
		}
		switch (axis) {
		case Y:
			return sign > 0 ? S : N;
		case X:
			return sign > 0 ? E : W;
		default:
			return NONE;
		}
	}
	
	public static Direction forX(int sign) {
		return forAxis(Axis.X, sign);
	}
	
	public static Direction forY(int sign) {
		return forAxis(Axis.Y, sign);
	}

}
