package me.capstone.advancedbattle.event.tile;

import me.capstone.advancedbattle.event.Cancellable;
import me.capstone.advancedbattle.event.HandlerList;
import me.capstone.advancedbattle.tile.Tile;

public class TileDespawnEvent extends AbstractTileEvent implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	
	public TileDespawnEvent(Tile t) {
		super(t);
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
