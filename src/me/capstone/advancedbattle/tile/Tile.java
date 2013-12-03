package me.capstone.advancedbattle.tile;

import me.capstone.advancedbattle.resources.tile.PieceTile;
import me.capstone.advancedbattle.tile.piece.Piece;

public class Tile {
	private int column;
	private int row;
	private int terrainTileID;
	private int structureTileID;
	private int pieceTileID;
	private Piece piece;
	
	public Tile(int column, int row, int terrainTileID, int structureTileID, int pieceTileID) {
		this.column = column;
		this.row = row;
		this.terrainTileID = terrainTileID;
		this.structureTileID = structureTileID;
		this.pieceTileID = pieceTileID;
		if (pieceTileID == 170) {
			this.piece = null;
		} else {
			this.piece = createPieceByID(pieceTileID);
		}
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
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
		for (PieceTile piece : PieceTile.values()) {
    		if (piece.getId() == id) {
    			return new Piece(piece);
    		}
    	}
		return null;
	}

}
