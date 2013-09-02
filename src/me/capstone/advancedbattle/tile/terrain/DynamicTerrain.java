package me.capstone.advancedbattle.tile.terrain;

import me.capstone.advancedbattle.data.tile.TileType;

// DynamicTerrain represents a terrain tile that can change states (neutral, own, states of capture, etc.)
public abstract class DynamicTerrain extends Terrain {

	protected DynamicTerrain(int row, int column, TileType type) {
		super(row, column, type);
	}

}
