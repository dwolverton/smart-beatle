package com.iwolverton.smartbeetle;

public class Stats {

	private int best, worst, average;

	public Stats(int best, int worst, int average) {
		super();
		this.best = best;
		this.worst = worst;
		this.average = average;
	}

	public int getBest() {
		return best;
	}

	public void setBest(int best) {
		this.best = best;
	}

	public int getWorst() {
		return worst;
	}

	public void setWorst(int worst) {
		this.worst = worst;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return "Stats [best=" + best + ", worst=" + worst + ", average="
				+ average + "]";
	}

}
