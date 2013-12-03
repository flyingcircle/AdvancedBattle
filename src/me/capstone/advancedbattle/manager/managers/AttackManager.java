package me.capstone.advancedbattle.manager.managers;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;

public class AttackManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	public AttackManager(GameManager game) {
		this.game = game;
	}

}
