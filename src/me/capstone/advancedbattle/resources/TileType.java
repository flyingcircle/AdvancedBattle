package me.capstone.advancedbattle.resources;

public interface TileType {
	
	/*///////////////////////
	 * 						/
	 * 		 TERRAIN		/
	 * 						/
	 *///////////////////////
	
	// Bridge
	public static final int BRIDGE_HORIZONTAL = 61;
	public static final int BRIDGE_VERTICAL = 70;
		
	// Cliff
	public static final int CLIFF_NW = 4;
	public static final int CLIFF_N = 5;
	public static final int CLIFF_NE = 6;
	public static final int CLIFF_E = 14;
	public static final int CLIFF_SE = 24;
	public static final int CLIFF_S = 23;
	public static final int CLIFF_SW = 22;
	public static final int CLIFF_W = 13;
	
	// Cliff w/rock
	public static final int CLIFF_ROCK_NW = 58;
	public static final int CLIFF_ROCK_N = 59;
	public static final int CLIFF_ROCK_NE = 60;
	public static final int CLIFF_ROCK_E = 69;
	public static final int CLIFF_ROCK_SE = 78;
	public static final int CLIFF_ROCK_S = 77;
	public static final int CLIFF_ROCK_SW = 76;
	public static final int CLIFF_ROCK_W = 67;
	public static final int CLIFF_ROCK_C = 68;
		
	// Cliff w/sides
	public static final int CLIFF_NEW = 34;
	public static final int CLIFF_EW = 43;
	public static final int CLIFF_SEW = 52;
	public static final int CLIFF_NSW = 79;
	public static final int CLIFF_NS = 80;
	public static final int CLIFF_NSE = 81;
	
	// Mountain
	public static final int SMALL_MOUNTAIN = 62;
	public static final int LARGE_MOUNTAIN = 54;
	
	// Mountain top
	public static final int MOUNTAIN_TOP_PLAIN = 45;
	public static final int MOUNTAIN_TOP_TREE = 71;
	public static final int MOUNTAIN_TOP_MOUNTAIN = 72;
		
	// River
	public static final int RIVER_NW = 1;
	public static final int RIVER_N = 2;
	public static final int RIVER_NE = 3;
	public static final int RIVER_E = 12;
	public static final int RIVER_SE = 21;
	public static final int RIVER_S = 20;
	public static final int RIVER_SW = 19;
	public static final int RIVER_W = 10;
	
	// River w/rocks
	public static final int RIVER_ROCK_N = 56;
	public static final int RIVER_ROCK_E = 66;
	public static final int RIVER_ROCK_S = 74;
	public static final int RIVER_ROCK_W = 64;
	public static final int RIVER_ROCK_C = 65;
	
	// Road (intersections)
	public static final int ROAD_NW = 28;
	public static final int ROAD_N = 29;
	public static final int ROAD_NE = 30;
	public static final int ROAD_E = 39;
	public static final int ROAD_SE = 48;
	public static final int ROAD_S = 47;
	public static final int ROAD_SW = 46;
	public static final int ROAD_W = 37;
	public static final int ROAD_C = 38;
	
	// Road (straight)
	public static final int ROAD_VERTICAL_TOP = 35;
	public static final int ROAD_HORIZONTAL = 44;
	public static final int ROAD_VERTICAL = 53;
	
	// Shoal
	public static final int SHOAL_NW = 31;
	public static final int SHOAL_N = 32;
	public static final int SHOAL_NE = 33;
	public static final int SHOAL_E = 42;
	public static final int SHOAL_SE = 51;
	public static final int SHOAL_S = 50;
	public static final int SHOAL_SW = 49;
	public static final int SHOAL_W = 40;
	
	// Shoal w/sides
	public static final int SHOAL_NSW = 55;
	public static final int SHOAL_NSE = 57;
	public static final int SHOAL_NEW = 73;
	public static final int SHOAL_SEW = 75;
	
	// Shoal edge (Beach)
	public static final int SHOAL_EDGE_NW = 7;
	public static final int SHOAL_EDGE_N = 8;
	public static final int SHOAL_EDGE_NE = 9;
	public static final int SHOAL_EDGE_E = 18;
	public static final int SHOAL_EDGE_SE = 27;
	public static final int SHOAL_EDGE_S = 26;
	public static final int SHOAL_EDGE_SW = 25;
	public static final int SHOAL_EDG_EW = 16;
	
	// Single
	public static final int OCEAN = 41;
	public static final int PLAIN = 11;
	public static final int POND = 36;
	public static final int ROCK = 14;
	public static final int TREE = 17;
	
	// Misc
	public static final int TERRAIN_NULL = 0;
	public static final int PLAIN_SHADOW = 63;
	
	/*///////////////////////
	 * 						/
	 * 		STRUCTURE		/
	 * 						/
	 *///////////////////////
	
	// City
	public static final int CITY_WHITE = 82;
	public static final int CITY_BLUE = 83;
	public static final int CITY_RED = 84;
	
	// Factory
	public static final int FACTORY_WHITE = 85;
	public static final int FACTORY_BLUE = 86;
	public static final int FACTORY_RED = 87;
	
	// HQ
	public static final int HQ_BLUE_TOP = 88;
	public static final int HQ_BLUE = 96;
	public static final int HQ_RED_TOP = 89;
	public static final int HQ_RED = 97;
	
	// Misc
	public static final int STRUCTURE_NULL = 90;
	
	/*///////////////////////
	 * 						/
	 * 		  PIECE			/
	 * 						/
	 *///////////////////////
	
	// Red
	public static final int RED_INFANTRY = 98;
	public static final int RED_MECH = 99;
	public static final int RED_NEO_TANK = 100;
	public static final int RED_TANK = 101;
	public static final int RED_RECON = 102;
	public static final int RED_APC = 103;
	public static final int RED_ARTILLERY = 104;
	public static final int RED_ROCKET = 105;
	public static final int RED_ANTI_AIR_TANK = 106;
	public static final int RED_ANTI_AIR_MISSILE = 107;
	public static final int RED_FIGHTER = 108;
	public static final int RED_BOMBER = 109;
	public static final int RED_BATTLE_HELICOPTER = 110;
	public static final int RED_TRANSPORT_HELICOPTER = 111;
	public static final int RED_BATTLESHIP = 112;
	public static final int RED_CRUISER = 113;
	public static final int RED_LANDER = 114;
	public static final int RED_SUBMARINE = 115;
	
	// Blue
	public static final int BLUE_INFANTRY = 116;
	public static final int BLUE_MECH = 117;
	public static final int BLUE_NEO_TANK = 118;
	public static final int BLUE_TANK = 119;
	public static final int BLUE_RECON = 120;
	public static final int BLUE_APC = 121;
	public static final int BLUE_ARTILLERY = 122;
	public static final int BLUE_ROCKET = 123;
	public static final int BLUE_ANTI_AIR_TANK = 124;
	public static final int BLUE_ANTI_AIR_MISSILE = 125;
	public static final int BLUE_FIGHTER = 126;
	public static final int BLUE_BOMBER = 127;
	public static final int BLUE_BATTLE_HELICOPTER = 128;
	public static final int BLUE_TRANSPORT_HELICOPTER = 129;
	public static final int BLUE_BATTLESHIP = 130;
	public static final int BLUE_CRUISER = 131;
	public static final int BLUE_LANDER = 132;
	public static final int BLUE_SUBMARINE = 133;
	
	// Green
	public static final int GREEN_INFANTRY = 134;
	public static final int GREEN_MECH = 135;
	public static final int GREEN_NEO_TANK = 136;
	public static final int GREEN_TANK = 137;
	public static final int GREEN_RECON = 138;
	public static final int GREEN_APC = 139;
	public static final int GREEN_ARTILLERY = 140;
	public static final int GREEN_ROCKET = 141;
	public static final int GREEN_ANTI_AIR_TANK = 142;
	public static final int GREEN_ANTI_AIR_MISSILE = 143;
	public static final int GREEN_FIGHTER = 144;
	public static final int GREEN_BOMBER = 145;
	public static final int GREEN_BATTLE_HELICOPTER = 146;
	public static final int GREEN_TRANSPORT_HELICOPTER = 147;
	public static final int GREEN_BATTLESHIP = 148;
	public static final int GREEN_CRUISER = 149;
	public static final int GREEN_LANDER = 150;
	public static final int GREEN_SUBMARINE = 151;
	
	// Yellow
	public static final int YELLOW_INFANTRY = 152;
	public static final int YELLOW_MECH = 153;
	public static final int YELLOW_NEO_TANK = 154;
	public static final int YELLOW_TANK = 155;
	public static final int YELLOW_RECON = 156;
	public static final int YELLOW_APC = 157;
	public static final int YELLOW_ARTILLERY = 158;
	public static final int YELLOW_ROCKET = 159;
	public static final int YELLOW_ANTI_AIR_TANK = 160;
	public static final int YELLOW_ANTI_AIR_MISSILE = 161;
	public static final int YELLOW_FIGHTER = 162;
	public static final int YELLOW_BOMBER = 163;
	public static final int YELLOW_BATTLE_HELICOPTER = 164;
	public static final int YELLOW_TRANSPORT_HELICOPTER = 165;
	public static final int YELLOW_BATTLESHIP = 166;
	public static final int YELLOW_CRUISER = 167;
	public static final int YELLOW_LANDER = 168;
	public static final int YELLOW_SUBMARINE = 169;
	
	// Misc
	public static final int PIECE_NULL = 170;
	
	/*///////////////////////
	 * 						/
	 * 		  CURSOR		/
	 * 						/
	 *///////////////////////
	
	public static final int CURSOR = 188;
	public static final int CURSOR_NULL = 189;
	
}
