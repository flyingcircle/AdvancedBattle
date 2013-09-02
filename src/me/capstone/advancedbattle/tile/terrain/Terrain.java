package me.capstone.advancedbattle.tile.terrain;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.Tile;

// Terrain represents a general game terrain tile (landscape or structure), not a piece.
public abstract class Terrain extends Tile {

	protected Terrain(int row, int column, TileType type) {
		super(row, column, type);
	}

}
