package me.capstone.advancedbattle.resources.data;

import org.andengine.util.color.Color;

public enum TeamColor {

	RED("RED", Color.RED),
	BLUE("BLUE", Color.BLUE),
	GREEN("GREEN", Color.GREEN),
	YELLOW("YELLOW", Color.YELLOW),
	NULL("NULL", Color.WHITE);
	
	private String name;
	private Color color;
	
	private TeamColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
		
}
