package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.player.Player;
import me.capstone.advancedbattle.tile.Tile;

public abstract class Piece extends Tile {
	
	private final int MAX_HEALTH;
	private int health;
	private final int MAX_AMMO;
	private int ammo;
	private final int MAX_FUEL;
	private int fuel;
	private int movement;
	private int vision;
	private boolean canLiberate;
	private Player owner;

	protected Piece(int row, int column, TileType type, int maxAmmo, int maxFuel, int movement, int vision, boolean canLiberate, Player owner) {
		super(row, column, type);
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
		this.MAX_AMMO = maxAmmo;
		this.ammo = MAX_AMMO;
		this.MAX_FUEL = maxFuel;
		this.fuel = MAX_FUEL;
		this.movement = movement;
		this.vision = vision;
		this.canLiberate = canLiberate;
		this.owner = owner;
	}

	protected int getHealth() {
		return this.health;
	}

	protected void setHealth(int health) {
		if(health > MAX_HEALTH)
			this.health = MAX_HEALTH;
		else
			this.health = health;
	}

	protected int getAmmo() {
		return this.ammo;
	}

	protected void setAmmo(int ammo) {
		if(ammo > MAX_AMMO)
			this.ammo = MAX_AMMO;
		else
			this.ammo = ammo;
	}
	
	protected int getFuel() {
		return this.fuel;
	}
	
	protected void setFuel(int fuel) {
		if(fuel > MAX_FUEL)
			this.fuel = MAX_FUEL;
		else
			this.fuel = fuel;
	}
	
	protected int getMovement() {
		return this.movement;
	}
	
	protected int getVision() {
		return this.vision;
	}
	
	protected boolean isCanLiberate() {
		return this.canLiberate;
	}
	
	protected Player getPlayer(){
		return this.owner;
	}
	
}