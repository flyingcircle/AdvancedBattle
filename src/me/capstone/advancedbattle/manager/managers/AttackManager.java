package me.capstone.advancedbattle.manager.managers;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.color.Color;

import me.capstone.advancedbattle.manager.GameManager;
import me.capstone.advancedbattle.resources.ResourcesManager;
import me.capstone.advancedbattle.resources.tile.CursorTile;
import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.resources.tile.TerrainTile;
import me.capstone.advancedbattle.tile.Tile;
import me.capstone.advancedbattle.tile.piece.Piece;
import me.capstone.advancedbattle.util.Util;

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
			if (sTile.getPiece() != null && Util.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getRow() == game.getMap().getRows() - 1) {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && Util.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getColumn() == 0) {
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && Util.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else if (tile.getColumn() == game.getMap().getColumns() - 1) {
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && Util.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				return true;
			}
		} else {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && Util.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && Util.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && Util.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				return true;
			}
			
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && Util.getPieceColor(wTile.getPiece()) != game.getTurn()) {
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
				
		if (!attacks.isEmpty()) {
			hide(attacks);
		}
		
		this.attackingPieceTile = null;
		this.attacks = null;
		
		if (successful) {
			game.disable(tile);
		}
		
		this.isAttacking = false;
	}
	
	public void executeAttackAction(Tile tile) {
		Piece attacker = attackingPieceTile.getPiece();
		Piece target = tile.getPiece();
		
		if (Util.isInfantry(attacker.getPieceTile())) {
			if (Util.isInfantry(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.55);
			} else if (Util.isMech(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.45);
			} else if (Util.isRecon(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.12);
			} else if (Util.isTank(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.05);
			}
		} else if (Util.isMech(attacker.getPieceTile())) {
			if (Util.isInfantry(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.65);
			} else if (Util.isMech(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.55);
			} else if (Util.isRecon(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.85);
			} else if (Util.isTank(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.55);
			}
		} else if (Util.isRecon(attacker.getPieceTile())) {
			if (Util.isInfantry(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.70);
			} else if (Util.isMech(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.65);
			} else if (Util.isRecon(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.35);
			} else if (Util.isTank(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.06);
			}
		} else if (Util.isTank(attacker.getPieceTile())) {
			if (Util.isInfantry(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.75);
			} else if (Util.isMech(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.70);
			} else if (Util.isRecon(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.85);
			} else if (Util.isTank(target.getPieceTile())) {
				dealDamage(attackingPieceTile, tile, 0.55);
			}
		}
		
		destroyAttackAction(true);
	}
	
	private void dealDamage(Tile attackerTile, Tile targetTile, double damage) {
		Piece attacker = attackerTile.getPiece();
		Piece target = targetTile.getPiece();
		
		int targetDefense = 0;
		if (targetTile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && targetTile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && targetTile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
    		for (TerrainTile terrain : TerrainTile.values()) {
    			if (terrain.getId() == targetTile.getStructureTileID()) {
    				targetDefense = terrain.getDefense();
    				break;
    			}
    		}
    	} else {
    		for (TerrainTile terrain : TerrainTile.values()) {
    			if (terrain.getId() == targetTile.getTerrainTileID()) {
    				targetDefense = terrain.getDefense();
    				break;
    			}
    		}
    	}
		
		int attackerDefense = 0;
		if (attackerTile.getStructureTileID() != TerrainTile.STRUCTURE_NULL.getId() && attackerTile.getStructureTileID() != TerrainTile.HQ_BLUE_TOP.getId() && attackerTile.getStructureTileID() != TerrainTile.HQ_RED_TOP.getId()) {
    		for (TerrainTile terrain : TerrainTile.values()) {
    			if (terrain.getId() == attackerTile.getStructureTileID()) {
    				attackerDefense = terrain.getDefense();
    				break;
    			}
    		}
    	} else {
    		for (TerrainTile terrain : TerrainTile.values()) {
    			if (terrain.getId() == attackerTile.getTerrainTileID()) {
    				attackerDefense = terrain.getDefense();
    				break;
    			}
    		}
    	}
		
		Random rand = new Random();
		int x;
		
		int oDamage;
		x = rand.nextInt(1);
		if (x == 0) {
			double temp = attacker.getHealth() * damage;
			oDamage  = (int) Math.floor(temp - temp * (targetDefense / 10));
		} else {
			double temp = attacker.getHealth() * damage;
			oDamage = (int) Math.floor(temp - temp * (targetDefense / 10)) + 1;
		}
				
		target.setHealth(target.getHealth() - oDamage);			
		if (target.getHealth() == 0) {
			destroy(targetTile);
		} else {
			int dDamage;
			x = rand.nextInt(1);
			if (x == 0) {
				double temp = target.getHealth() * damage;
				dDamage = (int) Math.floor(temp - temp * (attackerDefense / 10));
			} else {
				double temp = target.getHealth() * damage;
				dDamage = (int) Math.floor(temp - temp * (attackerDefense / 10)) + 1;
			}
			
			attacker.setHealth(attacker.getHealth() - dDamage);
			if (attacker.getHealth() == 0) {
				destroy(attackerTile);
			}
		}
	}
	
	private void destroy(Tile tile) {
		tile.setPiece(null);
		tile.setPieceTileID(PieceTile.PIECE_NULL.getId());
		
		TMXLayer pieceLayer = resourcesManager.getGameMap().getTMXLayers().get(2);
		TMXTile destroyed = pieceLayer.getTMXTile(tile.getColumn(), tile.getRow());
		destroyed.setGlobalTileID(resourcesManager.getGameMap(), PieceTile.PIECE_NULL.getId());
		pieceLayer.setIndex(destroyed.getTileRow() * resourcesManager.getGameMap().getTileColumns() + destroyed.getTileColumn());
		pieceLayer.drawWithoutChecks(destroyed.getTextureRegion(), destroyed.getTileX(), destroyed.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
		pieceLayer.submit();
	}
	
	private ArrayList<Tile> getNearbyAttacks(Tile tile) {
		ArrayList<Tile> attacks = new ArrayList<Tile>();
		
		if (tile.getRow() == 0) {
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && Util.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(sTile)) {
					attacks.add(sTile);
				}
			}
		} else if (tile.getRow() == game.getMap().getRows() - 1) {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && Util.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(nTile)) {
					attacks.add(nTile);
				}
			}
		} else if (tile.getColumn() == 0) {
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && Util.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(eTile)) {
					attacks.add(eTile);
				}
			}
		} else if (tile.getColumn() == game.getMap().getColumns() - 1) {
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && Util.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(wTile)) {
					attacks.add(wTile);
				}
			}
		} else {
			Tile nTile = game.getMap().getTile(tile.getColumn(), tile.getRow() - 1);
			if (nTile.getPiece() != null && Util.getPieceColor(nTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(nTile)) {
					attacks.add(nTile);
				}
			}
			
			Tile sTile = game.getMap().getTile(tile.getColumn(), tile.getRow() + 1);
			if (sTile.getPiece() != null && Util.getPieceColor(sTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(sTile)) {
					attacks.add(sTile);
				}
			}
			
			Tile eTile = game.getMap().getTile(tile.getColumn() + 1, tile.getRow());
			if (eTile.getPiece() != null && Util.getPieceColor(eTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(eTile)) {
					attacks.add(eTile);
				}
			}
			
			Tile wTile = game.getMap().getTile(tile.getColumn() - 1, tile.getRow());
			if (wTile.getPiece() != null && Util.getPieceColor(wTile.getPiece()) != game.getTurn()) {
				if (!attacks.contains(wTile)) {
					attacks.add(wTile);
				}
			}
		}
		
		return attacks;
	}

	private void display(ArrayList<Tile> attacks) {
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile attack : attacks) {
			TMXTile targeted = statusLayer.getTMXTile(attack.getColumn(), attack.getRow());
			targeted.setGlobalTileID(resourcesManager.getGameMap(), CursorTile.TARGET.getId());
			statusLayer.setIndex(targeted.getTileRow() * resourcesManager.getGameMap().getTileColumns() + targeted.getTileColumn());
			statusLayer.drawWithoutChecks(targeted.getTextureRegion(), targeted.getTileX(), targeted.getTileY(), resourcesManager.getGameMap().getTileWidth(), resourcesManager.getGameMap().getTileHeight(), Color.WHITE_ABGR_PACKED_FLOAT);
			statusLayer.submit();
		}
	}
	
	private void hide(ArrayList<Tile> attacks) {
		TMXLayer statusLayer = resourcesManager.getGameMap().getTMXLayers().get(3);
		for (Tile attack : attacks) {
			TMXTile targeted = statusLayer.getTMXTile(attack.getColumn(), attack.getRow());
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
