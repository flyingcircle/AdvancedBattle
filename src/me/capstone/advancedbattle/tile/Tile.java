package me.capstone.advancedbattle.tile;

import me.capstone.advancedbattle.resources.TileType;
import me.capstone.advancedbattle.tile.building.Building;
import me.capstone.advancedbattle.tile.piece.Piece;

public class Tile {
	private TileType type;
	private Building building;
	private Piece piece;
	
	public Tile(TileType type, Building building, Piece piece) {
		this.type = type;
		this.building = building;
		this.piece = piece;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

}
