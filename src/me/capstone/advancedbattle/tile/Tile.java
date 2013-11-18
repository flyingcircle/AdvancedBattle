package me.capstone.advancedbattle.tile;

import me.capstone.advancedbattle.tile.building.Structure;
import me.capstone.advancedbattle.tile.piece.Piece;

public class Tile {
	private int terrainTileID;
	private Structure structure;
	private Piece piece;
	
	public Tile(int terrainTileID, int structureTileID, int pieceTileID) {
		this.terrainTileID = terrainTileID;
		this.structure = null;
		this.piece = null;
	}

	public int getTerrainTileID() {
		return terrainTileID;
	}

	public void setTerrainTileID(int terrainTileID) {
		this.terrainTileID = terrainTileID;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Structure createStructureByID(int id) {
		switch (id) {
		default:
			return null;
		}
	}
	
	public Piece createPieceByID(int id) {
		switch (id) {
		default:
			return null;
		}
	}

}
