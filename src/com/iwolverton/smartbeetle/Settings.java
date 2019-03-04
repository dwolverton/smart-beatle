package com.iwolverton.smartbeetle;

public class Settings {

	protected int antHillStartDelay = 80;
	protected int antHillStartFrequency = 50;
	protected double antHillFrequencyModifier = .9;
	protected int beetleStartCharge = 50;
	protected int beetleMaxCharge = 100;
	protected int beetleStartAmmo = 3;
	protected int totalAmmo = 4;
	protected int spiderStartDelay = 3;
	protected int spiderFrequency = 3;
	protected int chargeCostToMove = 2;
	protected int chargeCostToShoot = 2;
	protected int chargeCostToStay = 2;
	protected int recharge = 5;
	
	public Settings() {}
	
	public Settings(Settings from) {
		this.antHillStartDelay = from.antHillStartDelay;
		this.antHillStartFrequency = from.antHillStartFrequency;
		this.antHillFrequencyModifier = from.antHillFrequencyModifier;
		this.beetleStartCharge = from.beetleStartCharge;
		this.beetleMaxCharge = from.beetleMaxCharge;
		this.beetleStartAmmo = from.beetleStartAmmo;
		this.totalAmmo = from.totalAmmo;
		this.totalAmmo = from.totalAmmo;
		this.spiderStartDelay = from.spiderStartDelay;
		this.spiderFrequency = from.spiderFrequency;
		this.chargeCostToMove = from.chargeCostToMove;
		this.chargeCostToShoot = from.chargeCostToShoot;
		this.chargeCostToStay = from.chargeCostToStay;
		this.recharge = from.recharge;
	}

	public int getAntHillStartDelay() {
		return antHillStartDelay;
	}

	public int getAntHillStartFrequency() {
		return antHillStartFrequency;
	}

	public double getAntHillFrequencyModifier() {
		return antHillFrequencyModifier;
	}

	public int getBeetleStartCharge() {
		return beetleStartCharge;
	}

	public int getBeetleMaxCharge() {
		return beetleMaxCharge;
	}

	public int getBeetleStartAmmo() {
		return beetleStartAmmo;
	}

	public int getTotalAmmo() {
		return totalAmmo;
	}

	public int getSpiderStartDelay() {
		return spiderStartDelay;
	}

	public int getSpiderFrequency() {
		return spiderFrequency;
	}

	public int getChargeCostToMove() {
		return chargeCostToMove;
	}

	public int getChargeCostToShoot() {
		return chargeCostToShoot;
	}

	public int getChargeCostToStay() {
		return chargeCostToStay;
	}

	public int getRecharge() {
		return recharge;
	}

}
