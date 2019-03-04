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

	/**
	 * How many turns until the first ant appears.
	 */
	public int getAntHillStartDelay() {
		return antHillStartDelay;
	}

	/**
	 * After the first ant, how many turns before the next ant.
	 */
	public int getAntHillStartFrequency() {
		return antHillStartFrequency;
	}

	/**
	 * With each new ant the ant frequency is multiplied by this
	 * modifier to determine the next frequency. It should be in the
	 * range 0 < x <= 1.
	 */
	public double getAntHillFrequencyModifier() {
		return antHillFrequencyModifier;
	}

	/**
	 * The beetle's charge at the start of the game.
	 */
	public int getBeetleStartCharge() {
		return beetleStartCharge;
	}

	/**
	 * The maximum charge value the beetle can attain.
	 */
	public int getBeetleMaxCharge() {
		return beetleMaxCharge;
	}

	/**
	 * The amount of ammo the beetle has at the start of the game.
	 */
	public int getBeetleStartAmmo() {
		return beetleStartAmmo;
	}

	/**
	 * The total amount of bead + ammo in the game. When ammo is used
	 * it appears immediately as beads on the board.
	 */
	public int getTotalAmmo() {
		return totalAmmo;
	}

	/**
	 * How many turns before the spider first starts to move.
	 */
	public int getSpiderStartDelay() {
		return spiderStartDelay;
	}

	/**
	 * Once the spider starts moving, how many turns between moves.
	 * If 3, for example, the spider will pause 2 turns and move on the 3rd.
	 */
	public int getSpiderFrequency() {
		return spiderFrequency;
	}

	/**
	 * How much charge is expended in a move action.
	 */
	public int getChargeCostToMove() {
		return chargeCostToMove;
	}

	/**
	 * How much charge is expended in a shoot action.
	 */
	public int getChargeCostToShoot() {
		return chargeCostToShoot;
	}

	/**
	 * How much charge is expended in a stay action (i.e. when
	 * the beetle takes no action).
	 */
	public int getChargeCostToStay() {
		return chargeCostToStay;
	}

	/**
	 * When ending a turn on a charging pad, how much charge is added
	 * to the beetle's battery? Note: the action cost is still accrued
	 * when on the charging pad, so the final charge for a turn will
	 * be recharge - action cost.
	 */
	public int getRecharge() {
		return recharge;
	}

}
