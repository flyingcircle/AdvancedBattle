package me.capstone.advancedbattle.resources;

public enum TerrainTile {
	
	// Bridge
	BRIDGE_HORIZONTAL(61, "Bridge", 0, 1, 1, 1, 1, -1, -1, 1),
	BRIDGE_VERTICAL(70, "Bridge", 0, 1, 1, 1, 1, -1, -1, 1),
		
	// Cliff
	CLIFF_NW(4, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_N(5, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_NE(6, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_E(14, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_SE(24, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_S(23, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_SW(22, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_W(13, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	
	// Cliff w/rock
	CLIFF_ROCK_NW(58, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_N(59, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_NE(60, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_E(69, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_SE(78, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_S(77, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_SW(76, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_W(67, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_ROCK_C(68, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
		
	// Cliff w/sides
	CLIFF_NEW(34, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_EW(43, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_SEW(52, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_NSW(79, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_NS(80, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	CLIFF_NSE(81, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	
	// Mountain
	SMALL_MOUNTAIN(62, "Mountain", 4, 2, 1, -1, -1, -1, -1, 1),
	LARGE_MOUNTAIN(54, "Mountain", 4, 2, 1, -1, -1, -1, -1, 1),
	
	// Mountain top
	MOUNTAIN_TOP_PLAIN(45, "Plain", 1, 1, 1, 2, 1, -1, -1, 1),
	MOUNTAIN_TOP_TREE(71, "Forest", 2, 1, 1, 3, 2, -1, -1, 1),
	MOUNTAIN_TOP_MOUNTAIN(72, "Mountain", 4, 2, 1, -1, -1, -1, -1, 1),
		
	// River
	RIVER_NW(1, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_N(2, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_NE(3, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_E(12, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_SE(21, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_S(20, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_SW(19, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_W(10, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	
	// River w/rocks
	RIVER_ROCK_N(56, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_ROCK_E(66, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_ROCK_S(74, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_ROCK_W(64, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	RIVER_ROCK_C(65, "River", 0, 2, 1, -1, -1, -1, -1, 1),
	
	// Road (intersections)
	ROAD_NW(28, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_N(29, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_NE(30, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_E(39, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_SE(48, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_S(47, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_SW(46, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_W(37, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_C(38, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	
	// Road (straight)
	ROAD_VERTICAL_TOP(35, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_HORIZONTAL(44, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	ROAD_VERTICAL(53, "Road", 0, 1, 1, 1, 1, -1, -1, 1),
	
	// Shoal
	SHOAL_NW(31, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_N(32, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_NE(33, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_E(42, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_SE(51, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_S(50, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_SW(49, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_W(40, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	
	// Shoal w/sides
	SHOAL_NSW(55, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_NSE(57, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_NEW(73, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_SEW(75, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	
	// Shoal edge (Beach)
	SHOAL_EDGE_NW(7, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	SHOAL_EDGE_N(8, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_EDGE_NE(9, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	SHOAL_EDGE_E(18, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_EDGE_SE(27, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	SHOAL_EDGE_S(26, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	SHOAL_EDGE_SW(25, "Cliff", 0, -1, -1, -1, -1, -1, -1, 1),
	SHOAL_EDGE_W(16, "Shoal", 0, 1, 1, 1, 1, -1, 1, 1),
	
	// Single
	SEA(41, "Sea", 0, -1, -1, -1, -1, 1, 1, 1),
	PLAIN(11, "Plain", 1, 1, 1, 2, 1, -1, -1, 1),
	POND(36, "Pond", 0, -1, -1, -1, -1, -1, -1, 1),
	REEF(14, "Reef", 1, - 1, -1, -1, -1, 2, 2, 1),
	FOREST(17, "Forest", 2, 1, 1, 3, 2, -1, -1, 1),
	
	// Misc
	PLAIN_SHADOW(63, "Plain", 1, 1, 1, 2, 1, -1, -1, 1),
	TERRAIN_NULL(0, "Null", -1, -1, -1, -1, -1, -1, -1, -1),
	
	// Structure
	// City
	CITY_WHITE(82, "City", 3, 1, 1, 1, 1, -1, -1, 1),
	CITY_BLUE(83, "City", 3, 1, 1, 1, 1, -1, -1, 1),
	CITY_RED(84, "City", 3, 1, 1, 1, 1, -1, -1, 1),
	
	// Factory
	FACTORY_WHITE(85, "Factory", 3, 1, 1, 1, 1, -1, -1, 1),
	FACTORY_BLUE(86, "Factory", 3, 1, 1, 1, 1, -1, -1, 1),
	FACTORY_RED(87, "Factory", 3, 1, 1, 1, 1, -1, -1, 1),
	
	// HQ
	HQ_BLUE_TOP(88, "HQ", 4, 1, 1, 1, 1, -1, -1, 1),
	HQ_BLUE(96, "HQ", 4, 1, 1, 1, 1, -1, -1, 1),
	HQ_RED_TOP(89, "HQ", 4, 1, 1, 1, 1, -1, -1, 1),
	HQ_RED(97, "HQ", 4, 1, 1, 1, 1, -1, -1, 1),
	
	// Misc
	STRUCTURE_NULL(90, "Null", -1, -1, -1, -1, -1, -1, -1, -1);
	
	// Attributes
	final int id;
	final String name;
	final int defense;
	final int infantryMovement;
	final int mechMovement;
	final int tireMovement;
	final int treadMovement;
	final int seaMovement;
	final int landerMovement;
	final int airMovement;
	
	private TerrainTile(int id, String name, int defense, int infantryMovement, int mechMovement, int tireMovement, int treadMovement, int seaMovement, int landerMovement, int airMovement) {
		this.id = id;
		this.name = name;
		this.defense = defense;
		this.infantryMovement = infantryMovement;
		this.mechMovement = mechMovement;
		this.tireMovement = tireMovement;
		this.treadMovement = treadMovement;
		this.seaMovement = seaMovement;
		this.landerMovement = landerMovement;
		this.airMovement = airMovement;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getDefense() {
		return defense;
	}

	public int getInfantryMovement() {
		return infantryMovement;
	}

	public int getMechMovement() {
		return mechMovement;
	}

	public int getTireMovement() {
		return tireMovement;
	}

	public int getTreadMovement() {
		return treadMovement;
	}

	public int getSeaMovement() {
		return seaMovement;
	}

	public int getLanderMovement() {
		return landerMovement;
	}

	public int getAirMovement() {
		return airMovement;
	}
	
}
