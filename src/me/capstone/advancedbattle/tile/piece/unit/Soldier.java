package me.capstone.advancedbattle.tile.piece.unit;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.Piece;
import me.capstone.advancedbattle.player.Player;

//Everything that all soldiers should have in common.
public abstract class Soldier extends Piece {
	
	private final int MAX_BUILDING_HEALTH;
	private int currentBuildingHealth;
	
	protected Soldier(final int id, final int column, final int row, final int width, final int height, final ITextureRegion region, int maxAmmo, int maxFuel, int movement, int vision, Player owner) {
		super(id, column, row, width, height, region, maxAmmo, maxFuel, movement, vision, true, owner);
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
