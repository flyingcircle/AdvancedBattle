package me.capstone.advancedbattle.resources;

public enum PieceTile {
	
	// Red
	RED_INFANTRY(98, "Infantry", 1000, 3, 2, true, MovementType.INFANTRY),
	RED_MECH(99, "Mech", 3000, 2, 2, true, MovementType.MECH),
	RED_MD_TANK(100, "MD Tank", 16000, 5, 1, false, MovementType.TREAD),
	RED_TANK(101, "Tank", 7000, 6, 3, false, MovementType.TREAD),
	RED_RECON(102, "Recon", 4000, 8, 5, false, MovementType.TIRES),
	RED_APC(103, "APC", 5000, 6, 1, false, MovementType.TREAD),
	RED_ARTILLERY(104, "Artillery", 6000, 5, 1, false, MovementType.TREAD),
	RED_ROCKET(105, "Rocket", 15000, 5, 1, false, MovementType.TIRES),
	RED_ANTI_AIR_TANK(106, "Anti-Air", 8000, 6, 2, false, MovementType.TREAD),
	RED_ANTI_AIR_MISSILE(107, "Missile", 12000, 4, 5, false, MovementType.TIRES),
	RED_FIGHTER(108, "Fighter", 20000, 9, 2, false, MovementType.AIR),
	RED_BOMBER(109, "Bomber", 22000, 7, 2, false, MovementType.AIR),
	RED_BATTLE_COPTER(110, "Copter", 9000, 6, 3, false, MovementType.AIR),
	RED_TRANSPORT_COPTER(111, "Transport", 5000, 6, 2, false, MovementType.AIR),
	RED_BATTLESHIP(112, "Battleship", 28000, 5, 2, false, MovementType.SEA),
	RED_CRUISER(113, "Cruiser", 18000, 6, 3, false, MovementType.SEA),
	RED_LANDER(114, "Lander", 12000, 6, 1, false, MovementType.LANDER),
	RED_SUBMARINE(115, "Submarine", 20000, 5, 5, false, MovementType.SEA),
		
	// Blue
	BLUE_INFANTRY(116, "Infantry", 1000, 3, 2, true, MovementType.INFANTRY),
	BLUE_MECH(117, "Mech", 3000, 2, 2, true, MovementType.MECH),
	BLUE_MD_TANK(118, "MD Tank", 16000, 5, 1, false, MovementType.TREAD),
	BLUE_TANK(119, "Tank", 7000, 6, 3, false, MovementType.TREAD),
	BLUE_RECON(120, "Recon", 4000, 8, 5, false, MovementType.TIRES),
	BLUE_APC(121, "APC", 5000, 6, 1, false, MovementType.TREAD),
	BLUE_ARTILLERY(122, "Artillery", 6000, 5, 1, false, MovementType.TREAD),
	BLUE_ROCKET(123, "Rocket", 15000, 5, 1, false, MovementType.TIRES),
	BLUE_ANTI_AIR_TANK(124, "Anti-Air", 8000, 6, 2, false, MovementType.TREAD),
	BLUE_ANTI_AIR_MISSILE(125, "Missile", 12000, 4, 5, false, MovementType.TIRES),
	BLUE_FIGHTER(126, "Fighter", 20000, 9, 2, false, MovementType.AIR),
	BLUE_BOMBER(127, "Bomber", 22000, 7, 2, false, MovementType.AIR),
	BLUE_BATTLE_COPTER(128, "Copter", 9000, 6, 3, false, MovementType.AIR),
	BLUE_TRANSPORT_COPTER(129, "Transport", 5000, 6, 2, false, MovementType.AIR),
	BLUE_BATTLESHIP(130, "Battleship", 28000, 5, 2, false, MovementType.SEA),
	BLUE_CRUISER(131, "Cruiser", 18000, 6, 3, false, MovementType.SEA),
	BLUE_LANDER(132, "Lander", 12000, 6, 1, false, MovementType.LANDER),
	BLUE_SUBMARINE(133, "Submarine", 20000, 5, 5, false, MovementType.SEA),
	
	// Green
	GREEN_INFANTRY(134, "Infantry", 1000, 3, 2, true, MovementType.INFANTRY),
	GREEN_MECH(135, "Mech", 3000, 2, 2, true, MovementType.MECH),
	GREEN_MD_TANK(136, "MD Tank", 16000, 5, 1, false, MovementType.TREAD),
	GREEN_TANK(137, "Tank", 7000, 6, 3, false, MovementType.TREAD),
	GREEN_RECON(138, "Recon", 4000, 8, 5, false, MovementType.TIRES),
	GREEN_APC(139, "APC", 5000, 6, 1, false, MovementType.TREAD),
	GREEN_ARTILLERY(140, "Artillery", 6000, 5, 1, false, MovementType.TREAD),
	GREEN_ROCKET(141, "Rocket", 15000, 5, 1, false, MovementType.TIRES),
	GREEN_ANTI_AIR_TANK(142, "Anti-Air", 8000, 6, 2, false, MovementType.TREAD),
	GREEN_ANTI_AIR_MISSILE(143, "Missile", 12000, 4, 5, false, MovementType.TIRES),
	GREEN_FIGHTER(144, "Fighter", 20000, 9, 2, false, MovementType.AIR),
	GREEN_BOMBER(145, "Bomber", 22000, 7, 2, false, MovementType.AIR),
	GREEN_BATTLE_COPTER(146, "Copter", 9000, 6, 3, false, MovementType.AIR),
	GREEN_TRANSPORT_COPTER(147, "Transport", 5000, 6, 2, false, MovementType.AIR),
	GREEN_BATTLESHIP(148, "Battleship", 28000, 5, 2, false, MovementType.SEA),
	GREEN_CRUISER(149, "Cruiser", 18000, 6, 3, false, MovementType.SEA),
	GREEN_LANDER(150, "Lander", 12000, 6, 1, false, MovementType.LANDER),
	GREEN_SUBMARINE(151, "Submarine", 20000, 5, 5, false, MovementType.SEA),
	
	// Yellow
	YELLOW_INFANTRY(152, "Infantry", 1000, 3, 2, true, MovementType.INFANTRY),
	YELLOW_MECH(153, "Mech", 3000, 2, 2, true, MovementType.MECH),
	YELLOW_MD_TANK(154, "MD Tank", 16000, 5, 1, false, MovementType.TREAD),
	YELLOW_TANK(155, "Tank", 7000, 6, 3, false, MovementType.TREAD),
	YELLOW_RECON(156, "Recon", 4000, 8, 5, false, MovementType.TIRES),
	YELLOW_APC(157, "APC", 5000, 6, 1, false, MovementType.TREAD),
	YELLOW_ARTILLERY(158, "Artillery", 6000, 5, 1, false, MovementType.TREAD),
	YELLOW_ROCKET(159, "Rocket", 15000, 5, 1, false, MovementType.TIRES),
	YELLOW_ANTI_AIR_TANK(160, "Anti-Air", 8000, 6, 2, false, MovementType.TREAD),
	YELLOW_ANTI_AIR_MISSILE(161, "Missile", 12000, 4, 5, false, MovementType.TIRES),
	YELLOW_FIGHTER(162, "Fighter", 20000, 9, 2, false, MovementType.AIR),
	YELLOW_BOMBER(163, "Bomber", 22000, 7, 2, false, MovementType.AIR),
	YELLOW_BATTLE_COPTER(164, "Copter", 9000, 6, 3, false, MovementType.AIR),
	YELLOW_TRANSPORT_COPTER(165, "Transport", 5000, 6, 2, false, MovementType.AIR),
	YELLOW_BATTLESHIP(166, "Battleship", 28000, 5, 2, false, MovementType.SEA),
	YELLOW_CRUISER(167, "Cruiser", 18000, 6, 3, false, MovementType.SEA),
	YELLOW_LANDER(168, "Lander", 12000, 6, 1, false, MovementType.LANDER),
	YELLOW_SUBMARINE(169, "Submarine", 20000, 5, 5, false, MovementType.SEA),
	
	// Misc
	PIECE_NULL(170, "Null", -1, -1, -1, false, MovementType.NULL);
	
	// Attributes
	final int id;
	final String name;
	final int cost;
	final int movement;
	final int vision;
	final boolean canLiberate;
	final MovementType moveType;
		
	private PieceTile(int id, String name, int cost, int movement, int vision, boolean canLiberate, MovementType moveType) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.movement = movement;
		this.vision = vision;
		this.canLiberate = canLiberate;
		this.moveType = moveType;
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

	public boolean canLiberate() {
		return canLiberate;
	}

	public MovementType getMoveType() {
		return moveType;
	}

}
