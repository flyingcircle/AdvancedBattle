package me.capstone.advancedbattle.tile.piece.unit;
import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.Piece;

//This is where everything common to all vehicles should go. It is likely that we should have 
//some sort of subclass for land, sea, and air.
public abstract class Vehicle extends Piece{
	
	protected Vehicle(int row, int column, TileType type) {
		super(row, column, type);
		canLiberate = false;
	}
}
