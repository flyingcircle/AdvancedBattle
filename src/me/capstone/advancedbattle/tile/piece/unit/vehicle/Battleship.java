package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.unit.Vehicle;
import me.capstone.advancedbattle.player.Player;

// Class for the Battleship piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Battleship extends Vehicle {

	public Battleship(final int id, final int column, final int row, final ITextureRegion region, Player owner) {
		super(id, column, row, 16, 16, region, 6, 99, 5, 3, owner);
	}

}
