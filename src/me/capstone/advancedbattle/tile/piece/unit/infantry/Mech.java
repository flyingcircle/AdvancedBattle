package me.capstone.advancedbattle.tile.piece.unit.infantry;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.piece.unit.Soldier;
import me.capstone.advancedbattle.player.Player;

public class Mech extends Soldier {
	
	public Mech(int row, int column, Player owner) {
		super(row, column, TileType.MECH, 3, 70, 2, 2, owner);
	}

}
