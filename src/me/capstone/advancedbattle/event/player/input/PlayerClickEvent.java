package me.capstone.advancedbattle.event.player.input;

import me.capstone.advancedbattle.event.Cancellable;
import me.capstone.advancedbattle.event.HandlerList;
import me.capstone.advancedbattle.player.Player;
import me.capstone.advancedbattle.util.Vector2;

/**
 * Represents a player click event.
 */
public class PlayerClickEvent extends PlayerInputEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final boolean pressed;
	private final Vector2 pos;

	public PlayerClickEvent(Player p, boolean pressed, Vector2 pos) {
		super(p);
		this.pressed = pressed;
		this.pos = pos;
	}

	public boolean isPressed() {
		return pressed;
	}

	public Vector2 getPosition() {
		return pos;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		super.setCancelled(cancelled);
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
