package me.capstone.advancedbattle.tile.piece.unit.vehicle;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.unit.Vehicle;
import me.capstone.advancedbattle.player.Player;

//Class for the Recon piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Recon extends Vehicle {

	public Recon(final int id, final int column, final int row, final ITextureRegion region, Player owner) {
		super(id, column, row, 16, 16, region, -1, 80, 8, 5, owner);
	}
}
