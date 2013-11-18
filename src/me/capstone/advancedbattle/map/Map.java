package me.capstone.advancedbattle.map;

import me.capstone.advancedbattle.tile.Tile;

public class Map {
	private Tile[][] map;
	private int columns;
	private int rows;
	
	public Map(int columns, int rows) {
		this.map = new Tile[columns][rows];
		this.columns = columns;
		this.rows = rows;
	}
	
	public Tile getTile(int column, int row) {
		return map[column][row];
	}

	public Tile[][] getMap() {
		return map;
	}

	public void setMap(Tile[][] map) {
		this.map = map;
	}
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
