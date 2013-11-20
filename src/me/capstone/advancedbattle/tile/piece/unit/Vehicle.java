package me.capstone.advancedbattle.tile.piece.unit;

import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;

//This is where everything common to all vehicles should go. It is likely that we should have 
//some sort of subclass for land, sea, and air.
public abstract class Vehicle extends Piece {
	
	protected Vehicle(Tile tile, PieceTile piece) {
		super(tile, piece, false);
	}
}
