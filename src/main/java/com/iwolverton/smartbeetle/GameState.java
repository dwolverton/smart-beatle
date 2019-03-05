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

/**
 * The current state of the game at a given point in time.
 */
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
	
	/**
	 * Number of turns completed.
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * A list of exactly 2 charging pads. The beetle receives charge when
	 * it ends its turn on one of these.
	 */
	public List<ChargingPad> getChargingPads() {
		return Collections.unmodifiableList(chargingPads);
	}

	/**
	 * The ant hill. Ants periodically appear from this hill. The frequency
	 * increases as the game goes on. Check its <code>nextMove</code> property
	 * to see how many turns until the next ant--one means it will be this turn,
	 * immediately after the beetle's action.
	 */
	public AntHill getAntHill() {
		return antHill;
	}

	/**
	 * The beetle can collect beads to be used as ammunition against ants.
	 * <p>
	 * There is a constant total number of beads in the game. (the beetle's ammo +
	 * the beads on the field)
	 */
	public List<Bead> getBeads() {
		return Collections.unmodifiableList(beads);
	}

	/**
	 * Our protagonist, a robotic beetle that must keeps its battery charged
	 * while fighting off ants and avoiding the spider.
	 */
	public Beetle getBeetle() {
		return beetle;
	}

	/**
	 * The spider is unstoppable, but slow. It doesn't move every turn. Check
	 * its <code>nextMove</code> property to see how many turns until it moves
	 * again--one means it will be this turn, immediately after the beetle's action.
	 */
	public Spider getSpider() {
		return spider;
	}

	/**
	 * Ants always move toward the beetle. They move one space per turn, just like
	 * the beetle. They can be stopped by shooting beads (ammo) at them, but
	 * only when they are directly adjacent to the beetle in one of the four
	 * cardinal directions.
	 * <p>
	 * Ants spawn from the ant hill at intervals.
	 */
	public List<Ant> getAnts() {
		return Collections.unmodifiableList(ants);
	}

	/**
	 * Returns the beetle, spider, or ant at the given coordinate. No
	 * two of these are allowed to be in the same space at the same time.
	 */
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

	/**
	 * Returns the beetle, spider, or ant at the given coordinate. No
	 * two of these are allowed to be in the same space at the same time.
	 */
	public ActingElement getPlayerAt(Coord coordinate) {
		return getPlayerAt(coordinate.getX(), coordinate.getY());
	}

	/**
	 * Returns the charging pad, ant hill, or bead at the given coordinate. No
	 * two of these are allowed to be in the same space at the same time.
	 */
	public GameElement getTerrainAt(int x, int y) {
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

	/**
	 * Returns the charging pad, ant hill, or bead at the given coordinate. No
	 * two of these are allowed to be in the same space at the same time.
	 */
	public GameElement getTerrainAt(Coord coordinate) {
		return getTerrainAt(coordinate.getX(), coordinate.getY());
	}
	
}
