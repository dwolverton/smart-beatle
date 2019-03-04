package com.iwolverton.smartbeetle;

public enum Axis {

	X, Y, NONE;
	
	public Axis opposite() {
		switch (this) {
		case X: return Y;
		case Y: return X;
		default: return NONE;
		}
	}
	
}
