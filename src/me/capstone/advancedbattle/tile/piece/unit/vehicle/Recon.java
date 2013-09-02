package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Vehicle;

//Class for the Recon piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Recon extends Vehicle {

	public Recon(int row, int column) {
		super(row, column, TileType.RECON, -1, 80, 8, 5);
	}
}
