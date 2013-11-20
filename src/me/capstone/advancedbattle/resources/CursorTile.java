package me.capstone.advancedbattle.resources;

public enum CursorTile {
	
	// Cursor	
	CURSOR(188, "Cursor"),
		
	// Misc
	CURSOR_NULL(189, "Null");
	
	//Attributes
	final int id;
	final String name;
	
	private CursorTile(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CursorTile get(int id) {
		return values()[id];
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
