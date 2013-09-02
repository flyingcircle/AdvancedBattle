package me.capstone.advancedbattle.tile;

import me.capstone.advancedbattle.data.tile.TileType;

// A general Tile object. This object stores row, column, and tile type (name and image).
public abstract class Tile {
	private int row;
	private int column;
	private TileType type;
	
	protected Tile(int row, int column, TileType type) {
		this.row = row;
		this.column = column;
		this.type = type;
	}

	protected int getRow() {
		return row;
	}

	protected void setRow(int row) {
		this.row = row;
	}

	protected int getColumn() {
		return column;
	}

	protected void setColumn(int column) {
		this.column = column;
	}

	protected TileType getType() {
		return type;
	}

	protected void setType(TileType type) {
		this.type = type;
	}

}
