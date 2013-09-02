package me.capstone.advancedbattle.tile.terrain.landscape;

import me.capstone.advancedbattle.data.tile.TileType;
import me.capstone.advancedbattle.tile.terrain.Terrain;

//Class for the Plain terrain. Not much to see here. Creator passes in row/column and we send back that it has TileType.PLAIN.
public class Plain extends Terrain {

	public Plain(int row, int column) {
		super(row, column, TileType.PLAIN);
	}

}
