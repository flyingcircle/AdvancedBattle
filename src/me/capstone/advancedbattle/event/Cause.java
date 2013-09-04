package me.capstone.advancedbattle.event;

/**
 * Represents a cause of an event
 *
 * @param <T> source of the cause
 */
public abstract class Cause<T> {
	private static final int MAX_CAUSES = 100;
	private final Cause<?> parent;
	
	/**
	 * Gets the source of the action
	 */
	public abstract T getSource();
	
	/**
	 * Constructs a cause with no parent cause
	 */
	public Cause() {
		this(null);
	}
	
	/**
	 * Constructs a cause with a parent cause that was directly responsible for the action
	 */
	public Cause(Cause<?> parent) {
		this.parent = parent;
	}
	
	/**
	 * Gets the first cause in the parent-child series of causes that led to this.
	 *
	 * May terminate early to prevent infinite loops
	 *
	 * Note: Can be null if there is no parent
	 *
	 * @return first cause or null if there is no parent
	 */
	public final Cause<?> getFirstCause() {
		int causes = 0;
		Cause<?> main = this;
		while (causes < MAX_CAUSES) {
			if (main.getParent() != null) {
				main = main.getParent();
			} else {
				break;
			}
		}
		return main;
	}
	
	/**
	 * Gets the parent cause of this cause. <br/><br/> Note: Can be null if there is no parent
	 *
	 * @return parent cause or null if there is no parent
	 */
	public final Cause<?> getParent() {
		return parent;
	}

}
