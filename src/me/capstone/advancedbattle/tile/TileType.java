package me.capstone.advancedbattle.tile;

import java.io.File;
import java.io.IOException;

// TODO : Figure out how to load images in Android
// TileType is used to store information about tiles
public class TileType {
	public static final TileType BATTLESHIP = new TileType("Battleship", "");
	public static final TileType BUILDING = new TileType("Building", "/resources/AW1/Images/Terrain/building.bmp");
	public static final TileType INFANTRY = new TileType("Infantry", "");
	public static final TileType OCEAN = new TileType("Ocean", "/resources/AW1/Images/Terrain/ocean.bmp");
	public static final TileType PLAIN = new TileType("Plain", "/resources/AW1/Images/Terrain/plains.bmp");
	public static final TileType RECON = new TileType("Recon", "");
	private final String name;
	private final String path;
	
	public TileType(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}
	
	public Image getImage() throws IOException {
		return ImageIO.read(new File(path));
	}
}
