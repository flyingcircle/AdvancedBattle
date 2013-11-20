package me.capstone.advancedbattle.resources;

public enum PieceTile {
	
	// Red
	RED_INFANTRY(98, "Infantry", -1),
	RED_MECH(99, "Mech", -1),
	RED_NEO_TANK(100, "Neo Tank", -1),
	RED_TANK(101, "Tank", -1),
	RED_RECON(102, "Recon", -1),
	RED_APC(103, "APC", -1),
	RED_ARTILLERY(104, "Artillery", -1),
	RED_ROCKET(105, "Rocket", -1),
	RED_ANTI_AIR_TANK(106, "AA Tank", -1),
	RED_ANTI_AIR_MISSILE(107, "AA Missile", -1),
	RED_FIGHTER(108, "Fighter", -1),
	RED_BOMBER(109, "Bomber", -1),
	RED_BATTLE_COPTER(110, "Battle Copter", -1),
	RED_TRANSPORT_COPTER(111, "Trans. Copter", -1),
	RED_BATTLESHIP(112, "Battleship", -1),
	RED_CRUISER(113, "Cruiser", -1),
	RED_LANDER(114, "Lander", -1),
	RED_SUBMARINE(115, "Submarine", -1),
		
	// Blue
	BLUE_INFANTRY(116, "Infantry", -1),
	BLUE_MECH(117, "Mech", -1),
	BLUE_NEO_TANK(118, "Neo Tank", -1),
	BLUE_TANK(119, "Tank", -1),
	BLUE_RECON(120, "Recon", -1),
	BLUE_APC(121, "APC", -1),
	BLUE_ARTILLERY(122, "Artillery", -1),
	BLUE_ROCKET(123, "Rocket", -1),
	BLUE_ANTI_AIR_TANK(124, "AA Tank", -1),
	BLUE_ANTI_AIR_MISSILE(125, "AA Missile", -1),
	BLUE_FIGHTER(126, "Fighter", -1),
	BLUE_BOMBER(127, "Bomber", -1),
	BLUE_BATTLE_COPTER(128, "Battle Copter", -1),
	BLUE_TRANSPORT_COPTER(129, "Trans. Copter", -1),
	BLUE_BATTLESHIP(130, "Battleship", -1),
	BLUE_CRUISER(131, "Cruiser", -1),
	BLUE_LANDER(132, "Lander", -1),
	BLUE_SUBMARINE(133, "Submarine", -1),
	
	// Green
	GREEN_INFANTRY(134, "Infantry", -1),
	GREEN_MECH(135, "Mech", -1),
	GREEN_NEO_TANK(136, "Neo Tank", -1),
	GREEN_TANK(137, "Tank", -1),
	GREEN_RECON(138, "Recon", -1),
	GREEN_APC(139, "APC", -1),
	GREEN_ARTILLERY(140, "Artillery", -1),
	GREEN_ROCKET(141, "Rocket", -1),
	GREEN_ANTI_AIR_TANK(142, "AA Tank", -1),
	GREEN_ANTI_AIR_MISSILE(143, "AA Missile", -1),
	GREEN_FIGHTER(144, "Fighter", -1),
	GREEN_BOMBER(145, "Bomber", -1),
	GREEN_BATTLE_COPTER(146, "Battle Copter", -1),
	GREEN_TRANSPORT_COPTER(147, "Trans. Copter", -1),
	GREEN_BATTLESHIP(148, "Battleship", -1),
	GREEN_CRUISER(149, "Cruiser", -1),
	GREEN_LANDER(150, "Lander", -1),
	GREEN_SUBMARINE(151, "Submarine", -1),
	
	// Yellow
	YELLOW_INFANTRY(152, "Infantry", -1),
	YELLOW_MECH(153, "Mech", -1),
	YELLOW_NEO_TANK(154, "Neo Tank", -1),
	YELLOW_TANK(155, "Tank", -1),
	YELLOW_RECON(156, "Recon", -1),
	YELLOW_APC(157, "APC", -1),
	YELLOW_ARTILLERY(158, "Artillery", -1),
	YELLOW_ROCKET(159, "Rocket", -1),
	YELLOW_ANTI_AIR_TANK(160, "AA Tank", -1),
	YELLOW_ANTI_AIR_MISSILE(161, "AA Missile", -1),
	YELLOW_FIGHTER(162, "Fighter", -1),
	YELLOW_BOMBER(163, "Bomber", -1),
	YELLOW_BATTLE_COPTER(164, "Battle Copter", -1),
	YELLOW_TRANSPORT_COPTER(165, "Trans. Copter", -1),
	YELLOW_BATTLESHIP(166, "Battleship", -1),
	YELLOW_CRUISER(167, "Cruiser", -1),
	YELLOW_LANDER(168, "Lander", -1),
	YELLOW_SUBMARINE(169, "Submarine", -1),
	
	// Misc
	PIECE_NULL(170, "Null", -1);
	
	// Attributes
	final int id;
	final String name;
	final int maxAmmo;
	final int maxFuel;
	final int movement;
	final int vision;
		
	private PieceTile(int id, String name, int maxAmmo, int maxFuel, int movement, int vision) {
		this.id = id;
		this.name = name;
		this.maxAmmo = maxAmmo;
		this.maxFuel = maxFuel;
		this.movement = movement;
		this.vision = vision;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public int getMaxFuel() {
		return maxFuel;
	}

	public int getMovement() {
		return movement;
	}

	public int getVision() {
		return vision;
	}

}
