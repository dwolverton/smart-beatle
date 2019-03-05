package com.iwolverton.smartbeetle;

/**
 * Cardinal direction on the game field.
 * <p>
 * Note that in the game's coordinates,right (E) and down (S) are positive.
 */
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

	/**
	 * x-axis component of the direction: either -1, 0, or 1
	 */
	public int getX() {
		return x;
	}

	/**
	 * y-axis component of the direction: either -1, 0, or 1
	 */
	public int getY() {
		return y;
	}

	/**
	 * whether this is an x-axis or y-axis direction
	 */
	public Axis getAxis() {
		return axis;
	}
	
	/**
	 * on this direction's axis, is it positive or negative (1 or -1).
	 */
	public int getSign() {
		switch (this) {
		case S:
		case E:
			return 1;
		case N:
		case W:
			return -1;
		default:
			return 0;
		}
	}

	/**
	 * Return the coordinate that is 1 unit of this direction from the given coordinate.
	 */
	public Coord relativeTo(Coord coord) {
		return new Coord(coord.getX() + x, coord.getY() + y);
	}
	
	/**
	 * Return the coordinate that is dist units of this direction from the given coordinate.
	 */
	public Coord relativeTo(Coord coord, int dist) {
		return new Coord(coord.getX() + x * dist, coord.getY() + y * dist);
	}

	/**
	 * The opposite direction. E.g. N is the opposite of S.
	 */
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

	/**
	 * Get the direction that has the given sign on the given axis.
	 * @param sign any number, only the sign (positive, negative or 0) will be taken into account.
	 */
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
	
	/**
	 * Return the direction that is 90 degrees clockwise from this one.
	 */
	public Direction nextClockwise() {
		if (this == NONE) {
			return NONE;
		}
		return Direction.values()[(ordinal() + 1) % 4];
	}
	
	/**
	 * Return the direction that is 90 degrees counterclockwise from this one.
	 */
	public Direction nextCounterclockwise() {
		if (this == NONE) {
			return NONE;
		}
		return Direction.values()[(ordinal() + 3) % 4];
	}
	
	/**
	 * Get the direction that has the given sign on the x axis.
	 * @param sign any number, only the sign (positive, negative or 0) will be taken into account.
	 */
	public static Direction forX(int sign) {
		return forAxis(Axis.X, sign);
	}
	
	/**
	 * Get the direction that has the given sign on the y axis.
	 * @param sign any number, only the sign (positive, negative or 0) will be taken into account.
	 */
	public static Direction forY(int sign) {
		return forAxis(Axis.Y, sign);
	}

}
