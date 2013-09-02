package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.Tile;

// Piece represents a general game piece tile (land, air, or sea), not a terrain tile.
public abstract class Piece extends Tile {
	
	protected boolean canLiberate; //to decide if piece can take over a building.
	protected int health = 10;
	protected int movement; //movement range of piece.
	protected int ammo; //any unit with shells will have ammo.

	protected Piece(int row, int column, TileType type) {
		super(row, column, type);
	}
	
}
