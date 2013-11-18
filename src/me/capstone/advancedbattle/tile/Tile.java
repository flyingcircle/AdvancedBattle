package me.capstone.advancedbattle.tile;

import me.capstone.advancedbattle.tile.piece.Piece;

public class Tile {
	private int terrainTileID;
	private int structureTileID;
	private int pieceTileID;
	private Piece piece;
	
	public Tile(int terrainTileID, int structureTileID, int pieceTileID) {
		this.terrainTileID = terrainTileID;
		this.structureTileID = structureTileID;
		this.pieceTileID = pieceTileID;
		this.piece = createPieceByID(pieceTileID);
	}

	public int getTerrainTileID() {
		return terrainTileID;
	}

	public void setTerrainTileID(int terrainTileID) {
		this.terrainTileID = terrainTileID;
	}

	public int getStructureTileID() {
		return structureTileID;
	}

	public void setStructureTileID(int structureTileID) {
		this.structureTileID = structureTileID;
	}

	public int getPieceTileID() {
		return pieceTileID;
	}

	public void setPieceTileID(int pieceTileID) {
		this.pieceTileID = pieceTileID;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece createPieceByID(int id) {
		return null;
	}

}
