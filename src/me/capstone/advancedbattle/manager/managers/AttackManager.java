package me.capstone.advancedbattle.manager.managers;

import java.util.ArrayList;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.tile.CursorTile;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.tile.Tile;

public class AttackManager {
	private static ResourcesManager resourcesManager = ResourcesManager.getInstance();
	
	private GameManager game;
	
	private boolean isAttacking = false;
	private Tile attackingPieceTile = null;
	private ArrayList<Tile> attacks;
	
	public AttackManager(GameManager game) {
		this.game = game;
	}
	
	public boolean canAttack(Tile tile) {
		if (tile.getRow() == 0) {
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && game.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getRow() == game.getMap().getRows() - 1) {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && game.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getColumn() == 0) {
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && game.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getColumn() == game.getMap().getColumns() - 1) {
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && game.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && game.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && game.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && game.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && game.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				return true;
			}
		}
		return false;
	}
	
	public void createAttackAction() {
		this.isAttacking = true;
		
		this.attackingPieceTile = game.getMap().getTile(resourcesManager.getCursorColumn(), resourcesManager.getCursorRow());
		this.attacks = new ArrayList<Tile>();
		
		attacks = getNearbyAttacks(attackingPieceTile);
		
		if (!attacks.isEmpty()) {
			display(attacks);
		}
	}
	
	public void destroyAttackAction(boolean successful) {
		Tile tile = attackingPieceTile;
		
		this.isAttacking = false;
		
		if (!attacks.isEmpty()) {
			hide(attacks);
		}
		
		this.attackingPieceTile = null;
		this.attacks = null;
		
		if (successful) {
			game.disable(tile);
		}
	}
	
	public void executeAttackAction(int clickedX, int clickedY) {
		PieceTile attacker = attackingPieceTile.getPiece().getPieceTile();
		PieceTile target = game.getMap().getTile(clickedX, clickedY).getPiece().getPieceTile();
		
		if (attacker.getId() == PieceTile.RED_INFANTRY.getId() || attacker.getId() == PieceTile.BLUE_INFANTRY.getId()) {
			if (target.getId() == PieceTile.RED_INFANTRY.getId() || target.getId() == PieceTile.BLUE_INFANTRY.getId()) {

			}
		} else if (attacker.getId() == PieceTile.RED_MECH.getId() || attacker.getId() == PieceTile.BLUE_MECH.getId()) {
			
		} else if (attacker.getId() == PieceTile.RED_RECON.getId() || attacker.getId() == PieceTile.BLUE_RECON.getId()) {
			
		} else if (attacker.getId() == PieceTile.RED_TANK.getId() || attacker.getId() == PieceTile.BLUE_TANK.getId()) {
			
		} else {
			// Do nothing. Something went wrong if we made it here.
		}
	}
	
	private ArrayList<Tile> getNearbyAttacks(Tile tile) {
		ArrayList<Tile> attacks = new ArrayList<Tile>();
		
		if (tile.getRow() == 0) {
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && game.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(sTile)) {
					attacks.add(sTile);
				}
			}
		} else if (tile.getRow() == game.getMap().getRows() - 1) {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && game.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(nTile)) {
					attacks.add(nTile);
				}
			}
		} else if (tile.getColumn() == 0) {
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && game.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(eTile)) {
					attacks.add(eTile);
				}
			}
		} else if (tile.getColumn() == game.getMap().getColumns() - 1) {
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && game.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(wTile)) {
					attacks.add(wTile);
				}
			}
		} else {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && game.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(nTile)) {
					attacks.add(nTile);
				}
			}
			
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && game.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(sTile)) {
					attacks.add(sTile);
				}
			}
			
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && game.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(eTile)) {
					attacks.add(eTile);
				}
			}
			
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && game.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(wTile)) {
					attacks.add(wTile);
				}
			}
		}
		
		return attacks;
	}

	private void display(ArrayList<Tile> moves) {
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile move : moves) {
			TMXTile targeted = statusLayer.getTMXTile(move.getColumn(), move.getRow());
			targeted.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.TARGET.getId());
			statusLayer.setIndex(targeted.getTileRow() * resourcesManager.getGameMap().getTileColumns() + targeted.getTileColumn());
			statusLayer.drawWithoutChecks(targeted.getTextureRegion(), targeted.getTileX(), targeted.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			statusLayer.submit();
		}
	}
	
	private void hide(ArrayList<Tile> moves) {
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile move : moves) {
			TMXTile targeted = statusLayer.getTMXTile(move.getColumn(), move.getRow());
			targeted.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.CURSOR_NULL.getId());
			statusLayer.setIndex(targeted.getTileRow() * resourcesManager.getGameMap().getTileColumns() + targeted.getTileColumn());
			statusLayer.drawWithoutChecks(targeted.getTextureRegion(), targeted.getTileX(), targeted.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			statusLayer.submit();
		}
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public Tile getAttackingPieceTile() {
		return attackingPieceTile;
	}

	public void setAttackingPieceTile(Tile attackingPieceTile) {
		this.attackingPieceTile = attackingPieceTile;
	}

	public ArrayList<Tile> getAttacks() {
		return attacks;
	}

	public void setAttacks(ArrayList<Tile> attacks) {
		this.attacks = attacks;
	}

}
