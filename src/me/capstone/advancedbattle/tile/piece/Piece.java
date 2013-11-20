package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.resources.PieceTile;
import me.capstone.advancedbattle.tile.Tile;

public class Piece {
	
	private final int MAX_HEALTH;
	private int health;
	private Tile tile;
	private PieceTile piece;
	private boolean canLiberate;

	protected Piece(Tile tile, PieceTile piece, boolean canLiberate) {
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
		this.tile = tile;
		this.piece = piece;
		this.canLiberate = canLiberate;
	}

	protected int getHealth() {
		return this.health;
	}

	protected void setHealth(int health) {
		if(health > MAX_HEALTH)
			this.health = MAX_HEALTH;
		else
			this.health = health;
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	protected PieceTile getPiece() {
		return piece;
	}
	
	protected void setPiece(PieceTile piece) {
		this.piece = piece;
	}
	
	protected boolean isCanLiberate() {
		return this.canLiberate;
	}
	
}