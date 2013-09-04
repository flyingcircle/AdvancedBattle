package me.capstone.advancedbattle.event;

/**
 * Manages event registration through Listeners and EventExecutors,  It also handles calling of events, and delayed events.
 */
public interface EventManager {
	/**
	 * Calls an event with the given details
	 *
	 * @param event Event details
	 * @return Called event
	 */
	public <T extends Event> T callEvent(T event);

	/**
	 * Calls an event with the given details, on the next tick
	 *
	 * @param event Event details
	 */
	public <T extends Event> void callDelayedEvent(T event);

	/**
	 * Unregisters all the events in the given listener class
	 *
	 * @param listener Listener to unregister
	 */
	public void unRegisterEvents(Listener listener);

	/**
	 * Registers all the events in the given listener class
	 *
	 * @param listener Listener to register
	 */
	public void registerEvents(Listener listener);

	/**
	 * Registers the specified executor to the given event class
	 *
	 * @param event Event type to register
	 * @param priority Priority to register this event at
	 * @param executor EventExecutor to register
	 */
	public void registerEvent(Class<? extends Event> event, Order priority, EventExecutor executor);

}
