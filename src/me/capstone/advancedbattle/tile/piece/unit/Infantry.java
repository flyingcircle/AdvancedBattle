package me.capstone.advancedbattle.tile.piece.unit;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.Piece;

//Class for the Infantry piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Infantry extends Piece {

	public Infantry(int row, int column) {
		super(row, column, TileType.INFANTRY);
	}

}
