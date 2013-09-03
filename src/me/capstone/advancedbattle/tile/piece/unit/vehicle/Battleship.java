package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Vehicle;
import me.capstone.advancedbattle.player.Player;

// Class for the Battleship piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Battleship extends Vehicle {

	public Battleship(int row, int column, Player owner) {
		super(row, column, TileType.BATTLESHIP, 6, 99, 5, 3, owner);
	}

}
