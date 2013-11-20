package me.capstone.advancedbattle.resources;

public enum PieceTile {
	
	// Red
	RED_INFANTRY(98, "Infantry", 1000, 3, 2),
	RED_MECH(99, "Mech", 3000, 2, 2),
	RED_MD_TANK(100, "MD Tank", 16000, 5, 1),
	RED_TANK(101, "Tank", 7000, 6, 3),
	RED_RECON(102, "Recon", 4000, 8, 5),
	RED_APC(103, "APC", 5000, 6, 1),
	RED_ARTILLERY(104, "Artillery", 6000, 5, 1),
	RED_ROCKET(105, "Rocket", 15000, 5, 1),
	RED_ANTI_AIR_TANK(106, "AA Tank", 8000, 6, 2),
	RED_ANTI_AIR_MISSILE(107, "AA Missile", 12000, 4, 5),
	RED_FIGHTER(108, "Fighter", 20000, 9, 2),
	RED_BOMBER(109, "Bomber", 22000, 7, 2),
	RED_BATTLE_COPTER(110, "Battle Copter", 9000, 6, 3),
	RED_TRANSPORT_COPTER(111, "Trans. Copter", 5000, 6, 2),
	RED_BATTLESHIP(112, "Battleship", 28000, 5, 2),
	RED_CRUISER(113, "Cruiser", 18000, 6, 3),
	RED_LANDER(114, "Lander", 12000, 6, 1),
	RED_SUBMARINE(115, "Submarine", 20000, 5, 5),
		
	// Blue
	BLUE_INFANTRY(116, "Infantry", 1000, 3, 2),
	BLUE_MECH(117, "Mech", 3000, 2, 2),
	BLUE_MD_TANK(118, "MD Tank", 16000, 5, 1),
	BLUE_TANK(119, "Tank", 7000, 6, 3),
	BLUE_RECON(120, "Recon", 4000, 8, 5),
	BLUE_APC(121, "APC", 5000, 6, 1),
	BLUE_ARTILLERY(122, "Artillery", 6000, 5, 1),
	BLUE_ROCKET(123, "Rocket", 15000, 5, 1),
	BLUE_ANTI_AIR_TANK(124, "AA Tank", 8000, 6, 2),
	BLUE_ANTI_AIR_MISSILE(125, "AA Missile", 12000, 4, 5),
	BLUE_FIGHTER(126, "Fighter", 20000, 9, 2),
	BLUE_BOMBER(127, "Bomber", 22000, 7, 2),
	BLUE_BATTLE_COPTER(128, "Battle Copter", 9000, 6, 3),
	BLUE_TRANSPORT_COPTER(129, "Trans. Copter", 5000, 6, 2),
	BLUE_BATTLESHIP(130, "Battleship", 28000, 5, 2),
	BLUE_CRUISER(131, "Cruiser", 18000, 6, 3),
	BLUE_LANDER(132, "Lander", 12000, 6, 1),
	BLUE_SUBMARINE(133, "Submarine", 20000, 5, 5),
	
	// Green
	GREEN_INFANTRY(134, "Infantry", 1000, 3, 2),
	GREEN_MECH(135, "Mech", 3000, 2, 2),
	GREEN_MD_TANK(136, "MD Tank", 16000, 5, 1),
	GREEN_TANK(137, "Tank", 7000, 6, 3),
	GREEN_RECON(138, "Recon", 4000, 8, 5),
	GREEN_APC(139, "APC", 5000, 6, 1),
	GREEN_ARTILLERY(140, "Artillery", 6000, 5, 1),
	GREEN_ROCKET(141, "Rocket", 15000, 5, 1),
	GREEN_ANTI_AIR_TANK(142, "AA Tank", 8000, 6, 2),
	GREEN_ANTI_AIR_MISSILE(143, "AA Missile", 12000, 4, 5),
	GREEN_FIGHTER(144, "Fighter", 20000, 9, 2),
	GREEN_BOMBER(145, "Bomber", 22000, 7, 2),
	GREEN_BATTLE_COPTER(146, "Battle Copter", 9000, 6, 3),
	GREEN_TRANSPORT_COPTER(147, "Trans. Copter", 5000, 6, 2),
	GREEN_BATTLESHIP(148, "Battleship", 28000, 5, 2),
	GREEN_CRUISER(149, "Cruiser", 18000, 6, 3),
	GREEN_LANDER(150, "Lander", 12000, 6, 1),
	GREEN_SUBMARINE(151, "Submarine", 20000, 5, 5),
	
	// Yellow
	YELLOW_INFANTRY(152, "Infantry", 1000, 3, 2),
	YELLOW_MECH(153, "Mech", 3000, 2, 2),
	YELLOW_MD_TANK(154, "MD Tank", 16000, 5, 1),
	YELLOW_TANK(155, "Tank", 7000, 6, 3),
	YELLOW_RECON(156, "Recon", 4000, 8, 5),
	YELLOW_APC(157, "APC", 5000, 6, 1),
	YELLOW_ARTILLERY(158, "Artillery", 6000, 5, 1),
	YELLOW_ROCKET(159, "Rocket", 15000, 5, 1),
	YELLOW_ANTI_AIR_TANK(160, "AA Tank", 8000, 6, 2),
	YELLOW_ANTI_AIR_MISSILE(161, "AA Missile", 12000, 4, 5),
	YELLOW_FIGHTER(162, "Fighter", 20000, 9, 2),
	YELLOW_BOMBER(163, "Bomber", 22000, 7, 2),
	YELLOW_BATTLE_COPTER(164, "Battle Copter", 9000, 6, 3),
	YELLOW_TRANSPORT_COPTER(165, "Trans. Copter", 5000, 6, 2),
	YELLOW_BATTLESHIP(166, "Battleship", 28000, 5, 2),
	YELLOW_CRUISER(167, "Cruiser", 18000, 6, 3),
	YELLOW_LANDER(168, "Lander", 12000, 6, 1),
	YELLOW_SUBMARINE(169, "Submarine", 20000, 5, 5),
	
	// Misc
	PIECE_NULL(170, "Null", -1, -1, -1);
	
	// Attributes
	final int id;
	final String name;
	final int cost;
	final int movement;
	final int vision;
		
	private PieceTile(int id, String name, int cost, int movement, int vision) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.movement = movement;
		this.vision = vision;
	}
	
	public static PieceTile get(int id) {
		return values()[id];
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}

	public int getMovement() {
		return movement;
	}

	public int getVision() {
		return vision;
	}

}
