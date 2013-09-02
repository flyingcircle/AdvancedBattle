package me.capstone.advancedbattle.tile.terrain;

import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.TileType;

// Terrain represents a general game terrain tile (landscape or structure), not a piece.
public abstract class Terrain extends Tile {

	protected Terrain(int row, int column, TileType type) {
		super(row, column, type);
	}

}
