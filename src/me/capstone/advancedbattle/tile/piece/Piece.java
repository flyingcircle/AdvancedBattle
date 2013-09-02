package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.Tile;

// Piece represents a general game piece tile (land, air, or sea), not a terrain tile.
public abstract class Piece extends Tile {
	
	private int health;
	private int ammo;
	private int fuel;
	private int movement;
	private int vision;
	private boolean canLiberate;

	protected Piece(int row, int column, TileType type, int ammo, int fuel, int movement, int vision, boolean canLiberate) {
		super(row, column, type);
		this.health = 10;
		this.ammo = ammo;
		this.fuel = fuel;
		this.movement = movement;
		this.canLiberate = canLiberate;
	}

	protected int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}

	protected int getAmmo() {
		return ammo;
	}

	protected void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	protected int getFuel() {
		return fuel;
	}
	
	protected void setFuel(int fuel) {
		this.fuel = fuel;
	}
	
	protected int getMovement() {
		return movement;
	}
	
	protected void setMovement(int movement) {
		this.movement = movement;
	}
	
	protected int getVision() {
		return vision;
	}
	
	protected void setVision(int vision) {
		this.vision = vision;
	}
	
	protected boolean isCanLiberate() {
		return canLiberate;
	}

	protected void setCanLiberate(boolean canLiberate) {
		this.canLiberate = canLiberate;
	}
	
}
