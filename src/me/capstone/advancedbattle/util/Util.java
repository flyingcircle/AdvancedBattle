package me.capstone.advancedbattle.util;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.data.TeamColor;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class Util {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	public static TeamColor getPieceColor(Piece piece) {
		if (piece.getPieceTile().getId() >= 98 && piece.getPieceTile().getId() < 116) {
			return TeamColor.RED;
		} else if (piece.getPieceTile().getId() >= 116 && piece.getPieceTile().getId() < 134) {
			return TeamColor.BLUE;
		} else {
			return TeamColor.NULL;
		}
	}
	
	public static int getCurrentFunds() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return game.getRedFunds();
		} else {
			return game.getBlueFunds();
		}
	}
	
	public static void setCurrentFunds(int funds) {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			game.setRedFunds(funds);
		} else {
			game.setBlueFunds(funds);
		}
	}
	
	public static final PieceTile getTurnInfantry() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_INFANTRY;
		} else {
			return PieceTile.BLUE_INFANTRY;
		}
	}
	
	public static final PieceTile getTurnMech() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_MECH;
		} else {
			return PieceTile.BLUE_MECH;
		}
	}
	
	public static final PieceTile getTurnRecon() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_RECON;
		} else {
			return PieceTile.BLUE_RECON;
		}
	}
	
	public static final PieceTile getTurnTank() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_TANK;
		} else {
			return PieceTile.BLUE_TANK;
		}
	}
	
	public static int getBuyAmount() {
		int items = 1;
		if (getCurrentFunds() >= 1000) {
			items++;
		}
		
		if (getCurrentFunds() >= 3000) {
			items++;
		}
		
		if (getCurrentFunds() >= 4000) {
			items++;
		}
		
		if (getCurrentFunds() >= 7000) {
			items++;
		}
		
		return items;
	}

}
