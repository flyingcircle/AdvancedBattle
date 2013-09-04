package me.capstone.advancedbattle.event.tile;

import me.capstone.advancedbattle.event.Cancellable;
import me.capstone.advancedbattle.event.HandlerList;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.util.Vector2;

/**
 * Called when an Tile spawns into the world. Implements Cancellable. Canceling this event will prevent the entity from spawning in the world.
 */
public class TileSpawnEvent extends AbstractTileEvent implements Cancellable {
	private static HandlerList handlers = new HandlerList();
	private Vector2 pos;

	public TileSpawnEvent(Tile t, Vector2 pos) {
		super(t);
		this.pos = pos;
	}

	/**
	 * Gets the location in which spawning will take place.
	 *
	 * @return The location where spawning will take place.
	 */
	public Vector2 getPos() {
		return this.pos;
	}

	/**
	 * Sets the location in which spawning will take place.
	 *
	 * @param pos The new location where spawning will take place.
	 */
	public void setPos(Vector2 pos) {
		this.pos = pos;
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
