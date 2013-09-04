package me.capstone.advancedbattle.event;

import me.capstone.advancedbattle.exception.EventException;

public interface EventExecutor {

	public void execute(Event event) throws EventException;
	
}
