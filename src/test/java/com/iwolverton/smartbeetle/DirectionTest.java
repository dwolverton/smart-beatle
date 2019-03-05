package com.iwolverton.smartbeetle;

import static org.junit.jupiter.api.Assertions.*;
import static com.iwolverton.smartbeetle.Direction.*;

import org.junit.jupiter.api.Test;

public class DirectionTest {

	@Test
	public void testNextClockwise() {
		assertEquals(E, N.nextClockwise());
		assertEquals(S, E.nextClockwise());
		assertEquals(W, S.nextClockwise());
		assertEquals(N, W.nextClockwise());
		assertEquals(NONE, NONE.nextClockwise());
	}

	@Test
	public void testNextCounterclockwise() {
		assertEquals(W, N.nextCounterclockwise());
		assertEquals(N, E.nextCounterclockwise());
		assertEquals(E, S.nextCounterclockwise());
		assertEquals(S, W.nextCounterclockwise());
		assertEquals(NONE, NONE.nextCounterclockwise());
	}
	
	@Test
	public void testOpposite() {
		assertEquals(S, N.opposite());
		assertEquals(W, E.opposite());
		assertEquals(N, S.opposite());
		assertEquals(E, W.opposite());
		assertEquals(NONE, NONE.opposite());
	}
	
	@Test
	public void testGetAxis() {
		assertEquals(Axis.Y, N.getAxis());
		assertEquals(Axis.X, E.getAxis());
		assertEquals(Axis.Y, S.getAxis());
		assertEquals(Axis.X, W.getAxis());
		assertEquals(Axis.NONE, NONE.getAxis());
	}
	
	@Test
	public void testForAxis() {
		assertEquals(E, Direction.forAxis(Axis.X, 10));
		assertEquals(W, Direction.forAxis(Axis.X, -2));
		assertEquals(NONE, Direction.forAxis(Axis.X, 0));
		assertEquals(S, Direction.forAxis(Axis.Y, 7));
		assertEquals(N, Direction.forAxis(Axis.Y, -23));
		assertEquals(NONE, Direction.forAxis(Axis.Y, 0));
		assertEquals(NONE, Direction.forAxis(Axis.NONE, 10));
		assertEquals(NONE, Direction.forAxis(Axis.NONE, -10));
	}

}
