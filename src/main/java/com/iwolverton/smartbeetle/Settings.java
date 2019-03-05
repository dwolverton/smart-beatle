package com.iwolverton.smartbeetle;

public class Settings {

	protected int antHillStartDelay = 80;
	protected int antHillStartFrequency = 50;
	protected double antHillFrequencyModifier = .9;
	protected int beetleStartCharge = 50;
	protected int beetleMaxCharge = 100;
	protected int beetleStartAmmo = 3;
	protected int totalAmmo = 4;
	protected int spiderStartDelay = 2;
	protected int spiderFrequency = 2;
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
	 * The default settings. Same as calling <code>new Settings()</code>.
	 */
	public static Settings defaultSettings() {
		return new Settings();
	}
	
	/**
	 * Frequent ants and lots of ammo
	 */
	public static Settings alternateSettingsAbundantAntsAndAmmo() {
		Settings s = new Settings();
		s.antHillStartDelay = 13;
		s.antHillStartFrequency = 13;
		s.antHillFrequencyModifier = 1.0;
		s.beetleStartAmmo = 10;
		s.totalAmmo = 40;
		s.spiderFrequency = 3;
		return s;
	}
	
	/**
	 * No ants. But a faster spider.
	 */
	public static Settings alternateSettingsNoAnts() {
		Settings s = new Settings();
		s.antHillStartDelay = Integer.MAX_VALUE;
		s.beetleStartAmmo = 0;
		s.totalAmmo = 0;
		s.spiderFrequency = 2;
		s.beetleMaxCharge = 50;
		return s;
	}

	/**
	 * (default 80) How many turns until the first ant appears.
	 */
	public int getAntHillStartDelay() {
		return antHillStartDelay;
	}

	/**
	 * (default 50) After the first ant, how many turns before the next ant.
	 */
	public int getAntHillStartFrequency() {
		return antHillStartFrequency;
	}

	/**
	 * (default .9) With each new ant the ant frequency is multiplied by this
	 * modifier to determine the next frequency. It should be in the
	 * range 0 &lt; x &lt;= 1.
	 */
	public double getAntHillFrequencyModifier() {
		return antHillFrequencyModifier;
	}

	/**
	 * (default 50) The beetle's charge at the start of the game.
	 */
	public int getBeetleStartCharge() {
		return beetleStartCharge;
	}

	/**
	 * (default 100) The maximum charge value the beetle can attain.
	 */
	public int getBeetleMaxCharge() {
		return beetleMaxCharge;
	}

	/**
	 * (default 3) The amount of ammo the beetle has at the start of the game.
	 */
	public int getBeetleStartAmmo() {
		return beetleStartAmmo;
	}

	/**
	 * (default 4) The total amount of bead + ammo in the game. When ammo is used
	 * it appears immediately as beads on the field.
	 */
	public int getTotalAmmo() {
		return totalAmmo;
	}

	/**
	 * (default 2) How many turns before the spider first starts to move.
	 */
	public int getSpiderStartDelay() {
		return spiderStartDelay;
	}

	/**
	 * (default 2) Once the spider starts moving, how many turns between moves.
	 * If 3, for example, the spider will pause 2 turns and move on the 3rd.
	 */
	public int getSpiderFrequency() {
		return spiderFrequency;
	}

	/**
	 * (default 2) How much charge is expended in a move action.
	 */
	public int getChargeCostToMove() {
		return chargeCostToMove;
	}

	/**
	 * (default 2) How much charge is expended in a shoot action.
	 */
	public int getChargeCostToShoot() {
		return chargeCostToShoot;
	}

	/**
	 * (default 2) How much charge is expended in a stay action (i.e. when
	 * the beetle takes no action).
	 */
	public int getChargeCostToStay() {
		return chargeCostToStay;
	}

	/**
	 * (default 5) When ending a turn on a charging pad, how much charge is added
	 * to the beetle's battery? Note: the action cost is still accrued
	 * when on the charging pad, so the final charge for a turn will
	 * be recharge - action cost.
	 */
	public int getRecharge() {
		return recharge;
	}

}
