package com.iwolverton.smartbeetle;

/**
 * Since the Settings class is immutable. This builder is provided
 * to make it easy to tweak the settings before starting a game.
 */
public class SettingsBuilder extends Settings {

	public SettingsBuilder() {}
	
	public SettingsBuilder(Settings from) {
		super(from);
	}
	
	public Settings build() {
		return new Settings(this);
	}

	public SettingsBuilder setAntHillStartDelay(int antHillStartDelay) {
		this.antHillStartDelay = antHillStartDelay;
		return this;
	}

	public SettingsBuilder setAntHillStartFrequency(int antHillStartFrequency) {
		this.antHillStartFrequency = antHillStartFrequency;
		return this;
	}

	public SettingsBuilder setAntHillFrequencyModifier(double antHillFrequencyModifier) {
		this.antHillFrequencyModifier = antHillFrequencyModifier;
		return this;
	}

	public SettingsBuilder setBeetleStartCharge(int beetleStartCharge) {
		this.beetleStartCharge = beetleStartCharge;
		return this;
	}

	public SettingsBuilder setBeetleMaxCharge(int beetleMaxCharge) {
		this.beetleMaxCharge = beetleMaxCharge;
		return this;
	}

	public SettingsBuilder setBeetleStartAmmo(int beetleStartAmmo) {
		this.beetleStartAmmo = beetleStartAmmo;
		return this;
	}

	public SettingsBuilder setTotalAmmo(int totalAmmo) {
		this.totalAmmo = totalAmmo;
		return this;
	}

	public SettingsBuilder setSpiderStartDelay(int spiderStartDelay) {
		this.spiderStartDelay = spiderStartDelay;
		return this;
	}

	public SettingsBuilder setSpiderFrequency(int spiderFrequency) {
		this.spiderFrequency = spiderFrequency;
		return this;
	}

	public SettingsBuilder setChargeCostToMove(int chargeCostToMove) {
		this.chargeCostToMove = chargeCostToMove;
		return this;
	}

	public SettingsBuilder setChargeCostToShoot(int chargeCostToShoot) {
		this.chargeCostToShoot = chargeCostToShoot;
		return this;
	}

	public SettingsBuilder setChargeCostToStay(int chargeCostToStay) {
		this.chargeCostToStay = chargeCostToStay;
		return this;
	}

	public SettingsBuilder setRecharge(int recharge) {
		this.recharge = recharge;
		return this;
	}
}
