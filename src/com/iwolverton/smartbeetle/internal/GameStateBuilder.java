package com.iwolverton.smartbeetle.internal;

import java.util.Arrays;
import java.util.List;

import com.iwolverton.smartbeetle.GameState;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.FireAnt;
import com.iwolverton.smartbeetle.elements.Spider;

public class GameStateBuilder extends GameState {

	public GameStateBuilder(int turn, List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beetle beetle, Spider spider,
			List<FireAnt> fireAnts) {
		super(turn, chargingPads, antHill, beads, beetle, spider, fireAnts);
	}

	public GameStateBuilder(GameState from) {
		super(from);
	}
	
	public GameState build() {
		return new GameState(this);
	}

	public GameStateBuilder setTurn(int turn) {
		this.turn = turn;
		return this;
	}
	
	public GameStateBuilder incrementTurn() {
		this.turn++;
		return this;
	}

	public GameStateBuilder setChargingPads(List<ChargingPad> chargingPads) {
		this.chargingPads = chargingPads;
		return this;
	}

	public GameStateBuilder setAntHill(AntHill antHill) {
		this.antHill = antHill;
		return this;
	}

	public GameStateBuilder setBeads(List<Bead> beads) {
		this.beads = beads;
		return this;
	}

	public GameStateBuilder setBeetle(Beetle beetle) {
		this.beetle = beetle;
		return this;
	}

	public GameStateBuilder setSpider(Spider spider) {
		this.spider = spider;
		return this;
	}

	public GameStateBuilder setFireAnts(List<FireAnt> fireAnts) {
		this.fireAnts = fireAnts;
		return this;
	}
	
	public GameStateBuilder setFireAnts(FireAnt... fireAnts) {
		this.fireAnts = Arrays.asList(fireAnts);
		return this;
	}

}
