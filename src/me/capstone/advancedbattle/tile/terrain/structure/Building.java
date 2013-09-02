package me.capstone.advancedbattle.tile.terrain.structure;

import me.capstone.advancedbattle.tile.TileType;
import me.capstone.advancedbattle.tile.terrain.DynamicTerrain;

//Class for the Building terrain. It extends DynamicTerrain so we will eventually add more to this so it can change states.
public class Building extends DynamicTerrain {

	protected Building(int row, int column) {
		super(row, column, TileType.BUILDING);
	}

}
