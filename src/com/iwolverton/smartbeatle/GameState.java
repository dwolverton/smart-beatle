package com.iwolverton.smartbeatle;

import java.util.Collections;
import java.util.List;

import com.iwolverton.smartbeatle.elements.ActingElement;
import com.iwolverton.smartbeatle.elements.AntHill;
import com.iwolverton.smartbeatle.elements.Bead;
import com.iwolverton.smartbeatle.elements.Beatle;
import com.iwolverton.smartbeatle.elements.ChargingPad;
import com.iwolverton.smartbeatle.elements.FireAnt;
import com.iwolverton.smartbeatle.elements.GameElement;
import com.iwolverton.smartbeatle.elements.Spider;

public class GameState {

	public static final int FIELD_DIMENSION = 20;

	protected List<ChargingPad> chargingPads;
	protected AntHill antHill;
	protected List<Bead> beads;
	protected Beatle beatle;
	protected Spider spider;
	protected List<FireAnt> fireAnts;

	public GameState(List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beatle beatle, Spider spider,
			List<FireAnt> fireAnts) {
		this.chargingPads = chargingPads;
		this.antHill = antHill;
		this.beads = beads;
		this.beatle = beatle;
		this.spider = spider;
		this.fireAnts = fireAnts;
	}
	
	public GameState(GameState from) {
		this(from.chargingPads, from.antHill, from.beads, from.beatle, from.spider, from.fireAnts);
	}
	
	public GameState(GameState from, Beatle beatle) {
		this(from);
		this.beatle = beatle;
	}
	
	public GameState(GameState from, Beatle beatle, List<FireAnt> fireAnts) {
		this(from);
		this.beatle = beatle;
		this.fireAnts = fireAnts;
	}

	public List<ChargingPad> getChargingPads() {
		return Collections.unmodifiableList(chargingPads);
	}

	public AntHill getAntHill() {
		return antHill;
	}

	public List<Bead> getBeads() {
		return Collections.unmodifiableList(beads);
	}

	public Beatle getBeatle() {
		return beatle;
	}

	public Spider getSpider() {
		return spider;
	}

	public List<FireAnt> getFireAnts() {
		return Collections.unmodifiableList(fireAnts);
	}

	public ActingElement getPlayerAt(int x, int y) {
		if (beatle.isAt(x, y)) {
			return beatle;
		}
		if (spider.isAt(x, y)) {
			return spider;
		}
		for (ActingElement el : fireAnts) {
			if (el.isAt(x, y)) {
				return el;
			}
		}
		return null;
	}

	public ActingElement getPlayerAt(Coord coordinate) {
		return getPlayerAt(coordinate.getX(), coordinate.getY());
	}

	public GameElement getNonPlayerAt(int x, int y) {
		if (antHill.isAt(x, y)) {
			return antHill;
		}
		for (GameElement el : chargingPads) {
			if (el.isAt(x, y)) {
				return el;
			}
		}
		for (GameElement el : beads) {
			if (el.isAt(x, y)) {
				return el;
			}
		}
		return null;
	}

	public GameElement getNonPlayerAt(Coord coordinate) {
		return getNonPlayerAt(coordinate.getX(), coordinate.getY());
	}

	
}
