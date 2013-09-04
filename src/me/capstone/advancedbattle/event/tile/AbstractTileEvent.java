package me.capstone.advancedbattle.event.tile;

import me.capstone.advancedbattle.event.Event;
import me.capstone.advancedbattle.tile.Tile;

/**
 * Represents a Tile related event.
 */
public abstract class AbstractTileEvent extends Event implements TileEvent {
	private final Tile tile;
	
	public AbstractTileEvent(Tile t) {
		this.tile = t;
	}
	
	@Override
	public Tile getTile() {
		return this.tile;
	}

}
