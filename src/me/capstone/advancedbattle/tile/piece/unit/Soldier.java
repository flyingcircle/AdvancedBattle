package me.capstone.advancedbattle.tile.piece.unit;
import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.Piece;

//Everything that all soldiers should have in common.
public abstract class Soldier extends Piece {
	
	protected Soldier(int row, int column, TileType type, int ammo, int fuel, int movement, int vision) {
		super(row, column, type, ammo, fuel, movement, vision, true);
	}

}
