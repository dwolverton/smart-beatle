package com.iwolverton.smartbeatle.internal;

import java.util.List;

import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;

public class GameStateBuilder extends GameState {

	public GameStateBuilder(List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beatle beatle, Spider spider,
			List<FireAnt> fireAnts) {
		super(chargingPads, antHill, beads, beatle, spider, fireAnts);
	}

	public GameStateBuilder(GameState from) {
		super(from);
	}
	
	public GameState build() {
		return new GameState(this);
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

	public GameStateBuilder setBeatle(Beatle beatle) {
		this.beatle = beatle;
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

}
