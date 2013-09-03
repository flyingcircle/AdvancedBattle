package me.capstone.advancedbattle.tile.piece.unit;
import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.Piece;
import me.capstone.advancedbattle.player.Player;

//This is where everything common to all vehicles should go. It is likely that we should have 
//some sort of subclass for land, sea, and air.
public abstract class Vehicle extends Piece {
	
	protected Vehicle(int row, int column, TileType type, int ammo, int fuel, int movement, int vision, Player owner) {
		super(row, column, type, ammo, fuel, movement, vision, false, owner);
	}
}
