package me.capstone.advancedbattle.manager.managers;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.data.TeamColor;

public class EndTurnManager {	
	private GameManager game;
	
	public EndTurnManager(GameManager game) {
		this.game = game;
	}

	public void endTurn() {
		if (game.getTurn() == TeamColor.RED) {
			game.setTurn(TeamColor.BLUE);
			game.setBlueFunds(game.getBlueFunds() + game.getMap().getBlueCities() * 1000);
		} else if (game.getTurn() == TeamColor.BLUE) {
			game.setTurn(TeamColor.RED);
			game.setRedFunds(game.getRedFunds() + game.getMap().getRedCities() * 1000);
		} else {
			game.setTurn(TeamColor.NULL);
		}
		
		game.enableAllTiles();
		game.getHud().updateHUD();
	}
}
