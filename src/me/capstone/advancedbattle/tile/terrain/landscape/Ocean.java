package me.capstone.advancedbattle.tile.terrain.landscape;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.terrain.Terrain;

// Class for the Ocean terrain. Not much to see here. Creator passes in row/column and we send back that it has TileType.OCEAN.
public class Ocean extends Terrain {
	
	public Ocean(int row, int column) {
		super(row, column, TileType.OCEAN);
	}

}
