package me.capstone.advancedbattle.map;

import me.capstone.advancedbattle.tile.Tile;

public class Map {
	private Tile[][] map;
	private int columns;
	private int rows;
	private int blueCities = 0;
	private int redCities = 0;
	private int greedCities = 0;
	private int yellowCities = 0;
	
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

	public int getBlueCities() {
		return blueCities;
	}

	public void setBlueCities(int blueCities) {
		this.blueCities = blueCities;
	}

	public int getRedCities() {
		return redCities;
	}

	public void setRedCities(int redCities) {
		this.redCities = redCities;
	}

	public int getGreedCities() {
		return greedCities;
	}

	public void setGreedCities(int greedCities) {
		this.greedCities = greedCities;
	}

	public int getYellowCities() {
		return yellowCities;
	}

	public void setYellowCities(int yellowCities) {
		this.yellowCities = yellowCities;
	}
}
