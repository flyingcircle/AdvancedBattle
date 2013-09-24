package me.capstone.advancedbattle.tile.piece.unit.infantry;

import org.andengine.opengl.texture.region.ITextureRegion;

import me.capstone.advancedbattle.tile.piece.unit.Soldier;
import me.capstone.advancedbattle.player.Player;

//Class for the Infantry piece. We will put health, fuel, damage, movement speed, and sight in here.
public class Infantry extends Soldier {

	public Infantry(final int id, final int column, final int row, final ITextureRegion region, Player owner) {
		super(id, column, row, 16, 16, region, -1, 99, 3, 2, owner);
	}

}
