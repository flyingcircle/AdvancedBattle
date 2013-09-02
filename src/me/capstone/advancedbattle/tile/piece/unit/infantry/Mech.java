package me.capstone.advancedbattle.tile.piece.unit.infantry;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Soldier;

public class Mech extends Soldier {
	
	public Mech(int row, int column) {
		super(row, column, TileType.MECH, 2, 3);
	}

}
