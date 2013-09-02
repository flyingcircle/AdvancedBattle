package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.Tile;

// Piece represents a general game piece tile (land, air, or sea), not a terrain tile.
public abstract class Piece extends Tile {
	
	protected int health;
	protected int movement;
	protected int ammo;
	protected boolean canLiberate;

	protected Piece(int row, int column, TileType type, int movement, int ammo, boolean canLiberate) {
		super(row, column, type);
		this.health = 10;
		this.movement = movement;
		this.ammo = ammo;
		this.canLiberate = canLiberate;
	}

	protected int getHealth() {
		return health;
	}

	protected void setHealth(int health) {
		this.health = health;
	}

	protected int getMovement() {
		return movement;
	}

	protected void setMovement(int movement) {
		this.movement = movement;
	}

	protected int getAmmo() {
		return ammo;
	}

	protected void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	protected boolean isCanLiberate() {
		return canLiberate;
	}

	protected void setCanLiberate(boolean canLiberate) {
		this.canLiberate = canLiberate;
	}
	
}
