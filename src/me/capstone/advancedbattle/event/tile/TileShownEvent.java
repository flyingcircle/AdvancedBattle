package me.capstone.advancedbattle.event.tile;

import me.capstone.advancedbattle.event.HandlerList;
import me.capstone.advancedbattle.player.Player;
import me.capstone.advancedbattle.tile.Tile;

public class TileShownEvent extends AbstractTileEvent {
	private static HandlerList handlers = new HandlerList();
	private Player hiddenFrom;

	public TileShownEvent(Tile hidden, Player player) {
		super(hidden);
		hiddenFrom = player;
	}

	public Player getHiddenFrom() {
		return hiddenFrom;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
