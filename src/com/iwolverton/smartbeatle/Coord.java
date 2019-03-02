package com.iwolverton.smartbeatle;

public class Coord {

	protected int x, y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isAt(int x, int y) {
		return x == this.x && y == this.y;
	}
	
	public boolean isAt(Coord other) {
		return isAt(other.x, other.y);
	}
	
	public int rightAngleDistanceFrom(int x, int y) {
		return Math.abs(x - this.x) + Math.abs(y - this.y); 
	}
	
	public int rightAngleDistanceFrom(Coord other) {
		return rightAngleDistanceFrom(other.x, other.y);
	}
	
	public int diagonalDistanceFrom(int x, int y) {
		return Math.max(Math.abs(x - this.x), Math.abs(y - this.y)); 
	}
	
	public int diagonalDistanceFrom(Coord other) {
		return diagonalDistanceFrom(other.x, other.y);
	}
	
	public Direction directionTo(Coord other) {
		int xDiff = other.getX() - x;
		int yDiff = other.getY() - y;
		if (Math.abs(xDiff) >= Math.abs(yDiff)) {
			return xDiff > 0 ? Direction.E : Direction.W;
		} else {
			return yDiff > 0 ? Direction.S : Direction.N;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Coord) {
			Coord o = (Coord) other;
			return o.x == this.x && o.y == this.y;
		} else {
			return false;
		}
	}

}
