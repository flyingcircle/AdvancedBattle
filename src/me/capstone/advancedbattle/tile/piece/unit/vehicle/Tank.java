package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.unit.Vehicle;
import me.capstone.advancedbattle.player.Player;

public class Tank extends Vehicle {

	protected Tank(final int id, final int column, final int row, final ITextureRegion region, Player owner) {
		super(id, column, row, 16, 16, region, 6, 70, 6, 3, owner);
	}

}
