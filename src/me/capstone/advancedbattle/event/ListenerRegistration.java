package me.capstone.advancedbattle.event;

/**
 * Represents an EventExecutor's registration.
 */
public class ListenerRegistration {
	private final EventExecutor executor;
	private final Order orderSlot;
	private final Object owner;

	/**
	 * @param executor Listener this registration represents
	 * @param orderSlot Order position this registration is in
	 * @param owner object that created this registration
	 */
	public ListenerRegistration(final EventExecutor executor, final Order orderSlot, final Object owner) {
		this.executor = executor;
		this.orderSlot = orderSlot;
		this.owner = owner;
	}

	/**
	 * Gets the listener for this registration
	 *
	 * @return Registered Listener
	 */
	public EventExecutor getExecutor() {
		return executor;
	}

	/**
	 * Gets the Object for this registration
	 *
	 * @return Registered owner
	 */
	public Object getOwner() {
		return owner;
	}

	/**
	 * Gets the order slot for this registration
	 *
	 * @return Registered order
	 */
	public Order getOrder() {
		return orderSlot;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + this.executor.hashCode();
		hash = 97 * hash + this.orderSlot.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ListenerRegistration other = (ListenerRegistration) obj;
		if (!(this.executor).equals(other.executor)) {
			return false;
		}
		if (this.orderSlot != other.orderSlot) {
			return false;
		}
		return true;
	}

}
