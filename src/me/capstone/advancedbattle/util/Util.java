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
	
	public static boolean isInfantry(PieceTile pieceTile) {
		switch (pieceTile.getId()) {
		case 98:
			return true;
		case 116:
			return true;
		case 134:
			return true;
		case 152:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isMech(PieceTile pieceTile) {
		switch (pieceTile.getId()) {
		case 99:
			return true;
		case 117:
			return true;
		case 135:
			return true;
		case 153:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isRecon(PieceTile pieceTile) {
		switch (pieceTile.getId()) {
		case 102:
			return true;
		case 120:
			return true;
		case 138:
			return true;
		case 156:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isTank(PieceTile pieceTile) {
		switch (pieceTile.getId()) {
		case 101:
			return true;
		case 119:
			return true;
		case 137:
			return true;
		case 155:
			return true;
		default:
			return false;
		}
	}
	
	public static PieceTile getTurnInfantry() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_INFANTRY;
		} else {
			return PieceTile.BLUE_INFANTRY;
		}
	}
	
	public static PieceTile getTurnMech() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_MECH;
		} else {
			return PieceTile.BLUE_MECH;
		}
	}
	
	public static PieceTile getTurnRecon() {
		GameManager game = resourcesManager.getGameManager();
		
		if (game.getTurn() == TeamColor.RED) {
			return PieceTile.RED_RECON;
		} else {
			return PieceTile.BLUE_RECON;
		}
	}
	
	public static PieceTile getTurnTank() {
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
