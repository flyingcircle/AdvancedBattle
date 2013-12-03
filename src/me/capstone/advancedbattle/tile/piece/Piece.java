package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.resources.PieceTile;

public class Piece {
	
	private final int MAX_HEALTH;
	private int health;
	private final int MAX_BUILDING_HEALTH;
	private int currentBuildingHealth;
	private PieceTile pieceTile;

	public Piece(PieceTile pieceTile) {
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
		this.pieceTile = pieceTile;
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

	public PieceTile getPieceTile() {
		return pieceTile;
	}
	
	public void setPieceTile(PieceTile pieceTile) {
		this.pieceTile = pieceTile;
	}
	
}