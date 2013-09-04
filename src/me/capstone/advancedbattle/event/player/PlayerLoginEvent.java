package me.capstone.advancedbattle.event.player;

import me.capstone.advancedbattle.event.HandlerList;
import me.capstone.advancedbattle.player.Player;

/**
 * Called when a player is attempting to log in. We may want to allow or disallow them from logging in for some reason.
 */
public class PlayerLoginEvent extends AbstractPlayerEvent {
	private static HandlerList handlers = new HandlerList();
	private String message;
	private boolean allowed = true;
	
	public PlayerLoginEvent(Player p) {
		super(p);
	}
	
	public boolean isAllowed() {
		return allowed;
	}
	
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}
	
	/**
	 * Gets the message to use if the player cannot log in.
	 *
	 * @return Current message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message to use if the player cannot log in.
	 *
	 * @param message The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Allows the player to log in
	 */
	public void allow() {
		allowed = true;
	}

	/**
	 * Disallows the player from logging in, with the given reason
	 *
	 * @param message Kick message to display to the user
	 */
	public void disallow(String message) {
		allowed = false;
		this.message = message;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
