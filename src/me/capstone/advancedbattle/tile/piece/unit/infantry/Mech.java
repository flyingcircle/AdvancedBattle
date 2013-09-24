package me.capstone.advancedbattle.tile.piece.unit.infantry;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.unit.Soldier;
import me.capstone.advancedbattle.player.Player;

public class Mech extends Soldier {
	
	public Mech(final int id, final int column, final int row, final ITextureRegion region, Player owner) {
		super(id, column, row, 16, 16, region, 3, 70, 2, 2, owner);
	}

}
