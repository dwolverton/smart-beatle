package com.iwolverton.smartbeetle;

import java.util.Random;

/**
 * An x, y coordinate pair.
 * <p>
 * Coord also serves as the base class for all game elements, which means
 * that each game element has all of Coord's methods (inheritance) and can
 * be used anywhere a Coord is called for (polymorphism).
 * <p>
 * Note that in the game's coordinates,right (E) and down (S) are positive.
 */
public class Coord {
	
	private static Random rand = new Random();

	protected int x, y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord(Coord from) {
		this.x = from.x;
		this.y = from.y;
	}

	/**
	 * X coordinate: right (E) is positive.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Y coordinate: down (S) is positive.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Is this coordinate at the given x and y?
	 */
	public boolean isAt(int x, int y) {
		return x == this.x && y == this.y;
	}
	
	/**
	 * Is this coordinate at the same location as another coordinate?
	 */
	public boolean isAt(Coord other) {
		return isAt(other.x, other.y);
	}
	
	/**
	 * Is this coordinate out of bounds.
	 */
	public boolean isOutOfBounds() {
		return x < 0 || y < 0 || x >= GameState.FIELD_DIMENSION
				|| y >= GameState.FIELD_DIMENSION;
	}
	
	/**
	 * Get the distance from this coordinate to another coordinate making
	 * only cardinal direction moves (no diagonals). This is useful since
	 * the game only allows cardinal direction moves.
	 */
	public int rightAngleDistanceFrom(int x, int y) {
		return Math.abs(x - this.x) + Math.abs(y - this.y); 
	}
	
	/**
	 * Get the distance from this coordinate to another coordinate making
	 * only cardinal direction moves (no diagonals). This is useful since
	 * the game only allows cardinal direction moves.
	 */
	public int rightAngleDistanceFrom(Coord other) {
		return rightAngleDistanceFrom(other.x, other.y);
	}
	
	/**
	 * Get the distance from this coordinate to another coordinate allowing
	 * cardinal and diagonal moves. Game elements cannot actually move
	 * diagonally, but this can still be a useful measure.
	 */
	public int diagonalDistanceFrom(int x, int y) {
		return Math.max(Math.abs(x - this.x), Math.abs(y - this.y)); 
	}
	
	/**
	 * Get the distance from this coordinate to another coordinate allowing
	 * cardinal and diagonal moves. Game elements cannot actually move
	 * diagonally, but this can still be a useful measure.
	 */
	public int diagonalDistanceFrom(Coord other) {
		return diagonalDistanceFrom(other.x, other.y);
	}
	
	/**
	 * Get the direction of the edge closest to this coordinate. For example,
	 * the closest edge to coordinate x=3, y= 12 is <code>Direction.W</code>.
	 */
	public Direction directionToNearestEdge() {
		// distance from center
		float xFromCenter = GameState.FIELD_DIMENSION / 2 - x - .5f;
		float yFromCenter = GameState.FIELD_DIMENSION / 2 - y - .5f;
		if (Math.abs(yFromCenter) > Math.abs(xFromCenter)) {
			return Direction.forAxis(Axis.Y, (int) -Math.signum(yFromCenter));
		} else {
			return Direction.forAxis(Axis.X, (int) -Math.signum(xFromCenter));
		}
	}
	
	/**
	 * How far is the given edge from this coordinate? If the coordinate is right
	 * next to that edge, the distance is 0. For example, the coordinate x=3, y=12
	 * is 3 units from the <code>Direction.W</code> edge.
	 */
	public int distanceToEdge(Direction dir) {
		switch (dir) {
		case N: return y - 0;
		case S: return GameState.FIELD_DIMENSION - y - 1;
		case E: return GameState.FIELD_DIMENSION - x - 1;
		case W: return x - 0;
		default: return Integer.MAX_VALUE; // infinite
		}
	}
	
	/**
	 * Determine the direction most aimed toward another coordinate.
	 * In case of a tie, the x-axis coordinate is chosen. If the other
	 * coordinate is the same as this, NONE is returned.
	 */
	public Direction directionTo(Coord other) {
		if (x == other.x && y == other.y) {
			return Direction.NONE;
		}
		int xDiff = other.getX() - x;
		int yDiff = other.getY() - y;
		if (Math.abs(xDiff) >= Math.abs(yDiff)) {
			return xDiff > 0 ? Direction.E : Direction.W;
		} else {
			return yDiff > 0 ? Direction.S : Direction.N;
		}
	}
	
	/**
	 * Determine the direction most aimed toward another coordinate.
	 * In case of a tie, the y-axis coordinate is chosen. If the other
	 * coordinate is the same as this, NONE is returned.
	 */
	public Direction altDirectionTo(Coord other) {
		if (x == other.x && y == other.y) {
			return Direction.NONE;
		}
		int xDiff = other.getX() - x;
		int yDiff = other.getY() - y;
		if (Math.abs(xDiff) < Math.abs(yDiff)) {
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

	/**
	 * Generate a random coordinate on a single axis within the bounds of the map.
	 */
	public static int randCoordPart() {
		return rand.nextInt(GameState.FIELD_DIMENSION);
	}

	/**
	 * Generate a random coordinate within the bounds of the map.
	 */
	public static Coord randCoord() {
		return new Coord(randCoordPart(), randCoordPart());
	}

}
