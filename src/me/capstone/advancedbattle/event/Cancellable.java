package me.capstone.advancedbattle.event;

/**
 * Interface for events that can be cancelled, to prevent them from propagating to downstream handlers.
 */
public interface Cancellable {

	/**
	 * If an event stops propagating(ie, is cancelled) part way through an event slot, that slot will not cease execution, but future event slots will not be called.
	 * 
	 * @param cancelled True to set event as cancelled, false to set as not cancelled.
	 */
	public void setCancelled(boolean cancelled);
	
	/**
	 * Get event cancelled state.
	 * 
	 * @return whether event is cancelled
	 */
	public boolean isCancelled();
}
