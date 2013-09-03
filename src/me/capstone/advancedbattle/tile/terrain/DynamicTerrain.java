package me.capstone.advancedbattle.tile.terrain;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.player.Player;

// DynamicTerrain represents a terrain tile that can change states (neutral, own, states of capture, etc.)
public abstract class DynamicTerrain extends Terrain {
	
	private Player owner;

	protected DynamicTerrain(int row, int column, TileType type, Player owner) {
		super(row, column, type);
		this.owner = owner;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public void setOwner(Player newOwner){
		this.owner = newOwner;
		//also need to change image
	}

}
