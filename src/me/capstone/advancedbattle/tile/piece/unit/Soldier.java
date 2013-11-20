package me.capstone.advancedbattle.tile.piece.unit;

import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

//Everything that all soldiers should have in common.
public abstract class Soldier extends Piece {
	
	private final int MAX_BUILDING_HEALTH;
	private int currentBuildingHealth;
	
	protected Soldier(Tile tile, PieceTile piece) {
		super(tile, piece, true);
		this.MAX_BUILDING_HEALTH = 10;
		this.currentBuildingHealth = MAX_BUILDING_HEALTH;
	}
	
	public int getCurrentBuildingHealth() {
		return this.currentBuildingHealth;
	}
	
	public void setCurrentBuildingHealth(int newHealth) {
		if(newHealth > MAX_BUILDING_HEALTH) {
			this.currentBuildingHealth = MAX_BUILDING_HEALTH;
		} else {
			this.currentBuildingHealth = newHealth;
		}
	}
	
	public void resetBuildingHealth() {
		this.currentBuildingHealth = MAX_BUILDING_HEALTH;
	}

}
