package me.capstone.advancedbattle.tile.piece;

import me.capstone.advancedbattle.resources.PieceTile;

public class Piece {
	
	private final int MAX_HEALTH;
	private int health;
	private PieceTile piece;
	private boolean canLiberate;

	protected Piece(final int column, final int row, PieceTile piece, boolean canLiberate) {
		this.MAX_HEALTH = 10;
		this.health = MAX_HEALTH;
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