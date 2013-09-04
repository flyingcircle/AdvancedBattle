package me.capstone.advancedbattle.event.player.input;

import me.capstone.advancedbattle.event.player.AbstractPlayerEvent;
import me.capstone.advancedbattle.player.Player;

public abstract class PlayerInputEvent extends AbstractPlayerEvent {
	public PlayerInputEvent(Player p) {
		super(p);
	}

}
