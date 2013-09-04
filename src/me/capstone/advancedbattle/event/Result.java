package me.capstone.advancedbattle.event;

public enum Result {
	/**
	 * Deny the event. Depending on the event, the action indicated by the event will either not take place or will be reverted. Some actions may not be denied.
	 */
	DENY(false),
	/**
	 * Neither deny nor allow the event. The server will proceed with its normal handling.
	 */
	DEFAULT(false),
	/**
	 * Allow / Force the event. The action indicated by the event will take place if possible, even if the server would not normally allow the action. Some actions may not be allowed.
	 */
	ALLOW(true);
	private boolean result;

	private Result(boolean result) {
		this.result = result;
	}

	/**
	 * True if the event is allowed, and is taking normal operation. False if the event is denied. Null if neither allowed, nor denied. The server will continue to proceed with its normal handling.
	 *
	 * @return the event's resolution.
	 */
	public boolean getResult() {
		return result;
	}
}
