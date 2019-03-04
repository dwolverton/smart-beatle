package com.iwolverton.smartbeetle;

public class Stats {

	private Class<? extends BeetleAi> ai;
	private int best, worst, average;

	public Stats(Class<? extends BeetleAi> ai, int best, int worst,
			int average) {
		this.ai = ai;
		this.best = best;
		this.worst = worst;
		this.average = average;
	}

	public Class<? extends BeetleAi> getAi() {
		return ai;
	}

	public void setAi(Class<? extends BeetleAi> ai) {
		this.ai = ai;
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
		return String.format("Avg:%5s Best:%5s Worst%5s %s", average, best, worst, ai == null ? "" : ai.getSimpleName());
	}

}
