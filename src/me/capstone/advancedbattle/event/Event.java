package me.capstone.advancedbattle.event;

public abstract class Event {
	
	protected boolean cancelled = false;
	private boolean beenCalled = false;
	
	public abstract HandlerList getHandlers();
	
	protected String getEventName() {
		return getClass().getSimpleName();
	}
	
	@Override
	public String toString() {
		return getEventName() + " (" + this.getClass().getName() + ")";
	}
	
	protected void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public boolean hasBeenCalled() {
		return beenCalled;
	}
	
	void setHasBeenCalled(boolean beenCalled) {
		this.beenCalled = beenCalled;
	}

}
