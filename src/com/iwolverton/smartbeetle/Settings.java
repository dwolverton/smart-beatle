package com.iwolverton.smartbeetle;

public class Settings {

	private int antHillStartDelay = 100;
	private int antHillStartFrequency = 90;
	private double antHillFrequencyModifier = .9;
	private int beetleStartCharge = 50;
	private int beetleMaxCharge = 100;
	private int beetleStartAmmo = 1;
	private int totalAmmo = 4;
	private int spiderFrequency = 3;

	public int getAntHillStartDelay() {
		return antHillStartDelay;
	}

	public void setAntHillStartDelay(int antHillStartDelay) {
		this.antHillStartDelay = antHillStartDelay;
	}

	public int getAntHillStartFrequency() {
		return antHillStartFrequency;
	}

	public void setAntHillStartFrequency(int antHillStartFrequency) {
		this.antHillStartFrequency = antHillStartFrequency;
	}

	public double getAntHillFrequencyModifier() {
		return antHillFrequencyModifier;
	}

	public void setAntHillFrequencyModifier(double antHillFrequencyModifier) {
		this.antHillFrequencyModifier = antHillFrequencyModifier;
	}

	public int getBeetleStartCharge() {
		return beetleStartCharge;
	}

	public void setBeetleStartCharge(int beetleStartCharge) {
		this.beetleStartCharge = beetleStartCharge;
	}

	public int getBeetleMaxCharge() {
		return beetleMaxCharge;
	}

	public void setBeetleMaxCharge(int beetleMaxCharge) {
		this.beetleMaxCharge = beetleMaxCharge;
	}

	public int getBeetleStartAmmo() {
		return beetleStartAmmo;
	}

	public void setBeetleStartAmmo(int beetleStartAmmo) {
		this.beetleStartAmmo = beetleStartAmmo;
	}

	public int getTotalAmmo() {
		return totalAmmo;
	}

	public void setTotalAmmo(int totalAmmo) {
		this.totalAmmo = totalAmmo;
	}

	public int getSpiderFrequency() {
		return spiderFrequency;
	}

	public void setSpiderFrequency(int spiderFrequency) {
		this.spiderFrequency = spiderFrequency;
	}

}
