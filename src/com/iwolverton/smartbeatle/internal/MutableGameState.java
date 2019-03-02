package com.iwolverton.smartbeatle.internal;

import java.util.List;

import com.iwolverton.smartbeatle.GameState;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.Spider;

public class MutableGameState extends GameState {

	public MutableGameState(List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beatle beatle, Spider spider,
			List<FireAnt> fireAnts) {
		super(chargingPads, antHill, beads, beatle, spider, fireAnts);
	}

	public MutableGameState(GameState from) {
		super(from);
	}
	
	public MutableGameState(GameState from, Beatle beatle) {
		super(from);
		this.beatle = beatle;
	}

	public void setChargingPads(List<ChargingPad> chargingPads) {
		this.chargingPads = chargingPads;
	}

	public void setAntHill(AntHill antHill) {
		this.antHill = antHill;
	}

	public void setBeads(List<Bead> beads) {
		this.beads = beads;
	}

	public void setBeatle(Beatle beatle) {
		this.beatle = beatle;
	}

	public void setSpider(Spider spider) {
		this.spider = spider;
	}

	public void setFireAnts(List<FireAnt> fireAnts) {
		this.fireAnts = fireAnts;
	}

}
