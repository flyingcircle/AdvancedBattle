package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.tile.Tile;

public class Piece {
	
	private final int MAX_HEALTH;
	private int health;
	private final int MAX_BUILDING_HEALTH;
	private int currentBuildingHealth;
	private Tile tile;
	private PieceTile piece;

	public Piece(Tile tile, PieceTile piece) {
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
		this.tile = tile;
		this.piece = piece;
		this.MAX_BUILDING_HEALTH = 10;
		this.currentBuildingHealth = MAX_BUILDING_HEALTH;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		if(health > MAX_HEALTH)
			this.health = MAX_HEALTH;
		else
			this.health = health;
	}
	
	public int getCurrentBuildingHealth() {
		return currentBuildingHealth;
	}

	public void setCurrentBuildingHealth(int currentBuildingHealth) {
		this.currentBuildingHealth = currentBuildingHealth;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public PieceTile getPiece() {
		return piece;
	}
	
	public void setPiece(PieceTile piece) {
		this.piece = piece;
	}
	
}