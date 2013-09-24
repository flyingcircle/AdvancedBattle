package me.capstone.advancedbattle.tile.piece.unit;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.Piece;
import me.capstone.advancedbattle.player.Player;

//This is where everything common to all vehicles should go. It is likely that we should have 
//some sort of subclass for land, sea, and air.
public abstract class Vehicle extends Piece {
	
	protected Vehicle(final int id, final int column, final int row, final int width, final int height, final ITextureRegion region, int maxAmmo, int maxFuel, int movement, int vision, Player owner) {
		super(id, column, row, width, height, region, maxAmmo, maxFuel, movement, vision, false, owner);
	}
}
