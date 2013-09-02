package me.capstone.advancedbattle.data.tile;

import java.io.IOException;

// TileType is used to store information about tiles
public class TileType {
	public static final TileType BATTLESHIP = new TileType("Battleship", "");
	public static final TileType BUILDING = new TileType("Building", "/AW1/Images/Terrain/building.bmp");
	public static final TileType INFANTRY = new TileType("Infantry", "");
	public static final TileType MECH = new TileType("Mech", "");
	public static final TileType OCEAN = new TileType("Ocean", "/AW1/Images/Terrain/ocean.bmp");
	public static final TileType PLAIN = new TileType("Plain", "/AW1/Images/Terrain/plains.bmp");
	public static final TileType RECON = new TileType("Recon", "");
	public static final TileType TANK = new TileType("Tank", "");
	private final String name;
	private final String path;
	
	public TileType(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}
	
	public String getImagePath() throws IOException {
		return path;
	}
}
