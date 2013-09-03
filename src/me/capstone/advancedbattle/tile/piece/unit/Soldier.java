package me.capstone.advancedbattle.tile.piece.unit;
import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.Piece;
import me.capstone.advancedbattle.player.Player;

//Everything that all soldiers should have in common.
public abstract class Soldier extends Piece {
	
	private int currentBuildingHealth;
	private final int MAX_BUILDING_HEALTH;
	
	protected Soldier(int row, int column, TileType type, int ammo, int fuel, int movement, int vision, Player owner) {
		super(row, column, type, ammo, fuel, movement, vision, true, owner);
		currentBuildingHealth = 10;
		this.MAX_BUILDING_HEALTH = 10;
	}
	
	public int getCurrentBuildingHealth(){
		return this.currentBuildingHealth;
	}
	
	public void setCurrentBuildingHealth(int newHealth){
		if(newHealth > MAX_BUILDING_HEALTH)
			currentBuildingHealth = MAX_BUILDING_HEALTH;
		else
			currentBuildingHealth = newHealth;
	}

}
