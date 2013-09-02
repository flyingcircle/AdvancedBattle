package me.capstone.advancedbattle.tile.piece.unit.infantry;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Soldier;

//Class for the Infantry piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Infantry extends Soldier {

	public Infantry(int row, int column) {
		super(row, column, TileType.INFANTRY, -1, 99, 3, 2);
	}

}
