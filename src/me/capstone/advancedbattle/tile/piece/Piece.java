package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.resources.tile.PieceTile;

public class Piece {
	
	public final int MAX_HEALTH;
	private int health;
	public final int MAX_BUILDING_HEALTH;
	private int currentBuildingHealth;
	private PieceTile pieceTile;

	public Piece(PieceTile pieceTile) {
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
		this.MAX_BUILDING_HEALTH = 10;
		this.currentBuildingHealth = MAX_BUILDING_HEALTH;
		this.pieceTile = pieceTile;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		if (health > MAX_HEALTH) {
			this.health = MAX_HEALTH;
		} else if (health <= 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}
	
	public int getCurrentBuildingHealth() {
		return currentBuildingHealth;
	}

	public void setCurrentBuildingHealth(int currentBuildingHealth) {
		this.currentBuildingHealth = currentBuildingHealth;
	}

	public PieceTile getPieceTile() {
		return pieceTile;
	}
	
	public void setPieceTile(PieceTile pieceTile) {
		this.pieceTile = pieceTile;
	}
	
}