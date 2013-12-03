package me.capstone.advancedbattle.resources;

public enum CursorTile {
	
	// Cursor	
	CURSOR(188),
		
	// Misc
	CURSOR_NULL(189),
	
	// Status
	DISABLED(190),
	HIGHLIGHT(191),
	TARGET(192);
	
	//Attributes
	final int id;
	
	private CursorTile(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
