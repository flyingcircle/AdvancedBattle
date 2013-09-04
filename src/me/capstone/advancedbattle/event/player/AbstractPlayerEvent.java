package me.capstone.advancedbattle.event.player;

import me.capstone.advancedbattle.event.Event;
import me.capstone.advancedbattle.player.Player;

/**
 * Represents a player-related event.
 */
public abstract class AbstractPlayerEvent extends Event implements PlayerEvent {
	private final Player player;
	
	public AbstractPlayerEvent(Player p) {
		player = p;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}

}
