package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Vehicle;

public class Tank extends Vehicle {

	protected Tank(int row, int column, TileType type) {
		super(row, column, type, 6, 70, 6, 3);
	}

}
