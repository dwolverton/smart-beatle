package com.iwolverton.smartbeetle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoordTest {

	@Test
	public void testDiagonalDist1() {
		Coord c = new Coord(10, 10);
		assertEquals(0, c.diagonalDistanceFrom(10, 10));
	}

	@Test
	public void testDiagonalDist2() {
		Coord c = new Coord(10, 10);
		assertEquals(4, c.diagonalDistanceFrom(12, 14));
	}

	@Test
	public void testDiagonalDist3() {
		Coord c = new Coord(10, 10);
		assertEquals(4, c.diagonalDistanceFrom(10, 14));
	}

	@Test
	public void testDiagonalDist4() {
		Coord c = new Coord(10, 10);
		assertEquals(4, c.diagonalDistanceFrom(14, 10));
	}

	@Test
	public void testDiagonalDist5() {
		Coord c = new Coord(10, 10);
		assertEquals(3, c.diagonalDistanceFrom(12, 7));
	}

	@Test
	public void testDiagonalDist6() {
		Coord c = new Coord(10, 10);
		assertEquals(3, c.diagonalDistanceFrom(10, 7));
	}

	@Test
	public void testDiagonalDist7() {
		Coord c = new Coord(10, 10);
		assertEquals(3, c.diagonalDistanceFrom(7, 10));
	}

	@Test
	public void testDiagonalDist8() {
		Coord c = new Coord(13, 2);
		assertEquals(5, c.diagonalDistanceFrom(8, 5));
	}

	@Test
	public void testDiagonalDist9() {
		Coord c = new Coord(5, 15);
		assertEquals(6, c.diagonalDistanceFrom(4, 9));
	}

	@Test
	public void testRightAngleDist1() {
		Coord c = new Coord(10, 10);
		assertEquals(2, c.rightAngleDistanceFrom(12, 10));
	}

	@Test
	public void testRightAngleDist2() {
		Coord c = new Coord(10, 10);
		assertEquals(4, c.rightAngleDistanceFrom(10, 14));
	}

	@Test
	public void testRightAngleDist3() {
		Coord c = new Coord(10, 10);
		assertEquals(3, c.rightAngleDistanceFrom(7, 10));
	}

	@Test
	public void testRightAngleDist4() {
		Coord c = new Coord(10, 10);
		assertEquals(6, c.rightAngleDistanceFrom(10, 4));
	}

	@Test
	public void testRightAngleDist5() {
		Coord c = new Coord(10, 10);
		assertEquals(3, c.rightAngleDistanceFrom(12, 9));
	}

	@Test
	public void testRightAngleDist6() {
		Coord c = new Coord(10, 10);
		assertEquals(13, c.rightAngleDistanceFrom(2, 15));
	}

	@Test
	public void testRightAngleDist7() {
		Coord c = new Coord(8, 15);
		assertEquals(10, c.rightAngleDistanceFrom(3, 20));
	}

	@Test
	public void testNearestEdgeN() {
		Coord c = new Coord(12, 3);
		assertEquals(Direction.N, c.directionToNearestEdge());
	}

	@Test
	public void testNearestEdgeE() {
		Coord c = new Coord(17, 12);
		assertEquals(Direction.E, c.directionToNearestEdge());
	}

	@Test
	public void testNearestEdgeS() {
		Coord c = new Coord(9, 12);
		assertEquals(Direction.S, c.directionToNearestEdge());
	}

	@Test
	public void testNearestEdgeW() {
		Coord c = new Coord(3, 8);
		assertEquals(Direction.W, c.directionToNearestEdge());
	}
	
	@Test
	public void testNearestEdgeCenterE() {
		Coord c = new Coord(10, 10);
		assertEquals(Direction.E, c.directionToNearestEdge());
	}
	
	@Test
	public void testNearestEdgeCenterW() {
		Coord c = new Coord(10, 9);
		assertEquals(Direction.E, c.directionToNearestEdge());
	}
	
	@Test
	public void distanceToEdge() {
		Coord c = new Coord(10, 15);
		assertEquals(15, c.distanceToEdge(Direction.N));
		assertEquals(4, c.distanceToEdge(Direction.S));
		assertEquals(9, c.distanceToEdge(Direction.E));
		assertEquals(10, c.distanceToEdge(Direction.W));
	}

}
