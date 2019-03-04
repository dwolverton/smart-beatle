package com.iwolverton.smartbeetle;

import java.util.Collections;
import java.util.List;

import com.iwolverton.smartbeetle.elements.ActingElement;
import com.iwolverton.smartbeetle.elements.AntHill;
import com.iwolverton.smartbeetle.elements.Bead;
import com.iwolverton.smartbeetle.elements.Beetle;
import com.iwolverton.smartbeetle.elements.ChargingPad;
import com.iwolverton.smartbeetle.elements.Ant;
import com.iwolverton.smartbeetle.elements.GameElement;
import com.iwolverton.smartbeetle.elements.Spider;

public class GameState {

	public static final int FIELD_DIMENSION = 20;

	protected int turn;
	protected List<ChargingPad> chargingPads;
	protected AntHill antHill;
	protected List<Bead> beads;
	protected Beetle beetle;
	protected Spider spider;
	protected List<Ant> ants;

	public GameState(int turn, List<ChargingPad> chargingPads, AntHill antHill,
			List<Bead> beads, Beetle beetle, Spider spider,
			List<Ant> ants) {
		this.turn = turn;
		this.chargingPads = chargingPads;
		this.antHill = antHill;
		this.beads = beads;
		this.beetle = beetle;
		this.spider = spider;
		this.ants = ants;
	}
	
	public GameState(GameState from) {
		this(from.turn, from.chargingPads, from.antHill, from.beads, from.beetle, from.spider, from.ants);
	}
	
	public GameState(GameState from, Beetle beetle) {
		this(from);
		this.beetle = beetle;
	}
	
	public GameState(GameState from, Beetle beetle, List<Ant> ants) {
		this(from);
		this.beetle = beetle;
		this.ants = ants;
	}
	
	public int getTurn() {
		return turn;
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

	public Beetle getBeetle() {
		return beetle;
	}

	public Spider getSpider() {
		return spider;
	}

	public List<Ant> getAnts() {
		return Collections.unmodifiableList(ants);
	}

	public ActingElement getPlayerAt(int x, int y) {
		if (beetle.isAt(x, y)) {
			return beetle;
		}
		if (spider.isAt(x, y)) {
			return spider;
		}
		for (ActingElement el : ants) {
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
