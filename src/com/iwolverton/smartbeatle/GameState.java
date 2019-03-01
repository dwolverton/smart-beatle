package com.iwolverton.smartbeatle;

import java.util.Collections;
import java.util.List;

public class GameState {

	public static final int FIELD_DIMENSION = 20;

	private List<ChargingPad> chargingPads;
	private AntHill antHill;
	private List<Bead> beads;
	private Beatle beatle;
	private Spider spider;
	private List<FireAnt> fireAnts;

	public GameState(List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beatle beatle, Spider spider,
			List<FireAnt> fireAnts) {
		this.chargingPads = Collections.unmodifiableList(chargingPads);
		this.antHill = antHill;
		this.beads = Collections.unmodifiableList(beads);
		this.beatle = beatle;
		this.spider = spider;
		this.fireAnts = Collections.unmodifiableList(fireAnts);
	}

	public List<ChargingPad> getChargingPads() {
		return chargingPads;
	}

	public AntHill getAntHill() {
		return antHill;
	}

	public List<Bead> getBeads() {
		return beads;
	}

	public Beatle getBeatle() {
		return beatle;
	}

	public Spider getSpider() {
		return spider;
	}

	public List<FireAnt> getFireAnts() {
		return fireAnts;
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
