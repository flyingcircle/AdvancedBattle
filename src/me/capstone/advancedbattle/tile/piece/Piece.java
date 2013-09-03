package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.player.Player;
import me.capstone.advancedbattle.tile.Tile;

// Piece represents a general game piece tile (land, air, or sea), not a terrain tile.
public abstract class Piece extends Tile {
	
	private int health;
	private final int MAX_HEALTH;
	private int ammo;
	private final int MAX_AMMO;
	private int fuel;
	private final int MAX_FUEL;
	private int movement;
	private int vision;
	private boolean canLiberate;
	private Player owner;

	protected Piece(int row, int column, TileType type, int ammo, int fuel, int movement, int vision, boolean canLiberate, Player owner) {
		super(row, column, type);
		this.health = 10;
		this.MAX_HEALTH = 10;
		this.ammo = ammo;
		this.MAX_AMMO = ammo;
		this.fuel = fuel;
		this.MAX_FUEL = fuel;
		this.movement = movement;
		this.vision = vision;
		this.canLiberate = canLiberate;
		this.owner = owner;
	}

	protected int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		if(health > MAX_HEALTH)
			this.health = MAX_HEALTH;
		else
			this.health = health;
	}

	protected int getAmmo() {
		return ammo;
	}

	protected void setAmmo(int ammo) {
		if(ammo > MAX_AMMO)
			this.ammo = MAX_AMMO;
		else
			this.ammo = ammo;
	}
	
	protected int getFuel() {
		return fuel;
	}
	
	protected void setFuel(int fuel) {
		if(fuel > MAX_FUEL)
			this.fuel = MAX_FUEL;
		else
			this.fuel = fuel;
	}
	
	protected int getMovement() {
		return movement;
	}
	
	protected int getVision() {
		return vision;
	}
	
	protected boolean isCanLiberate() {
		return canLiberate;
	}
	
	protected Player getPlayer(){
		return this.owner;
	}
	
}