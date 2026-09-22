package edu.towson.cis.cosc442.project1.monopoly;

public class GoCell extends Cell {
	/**
	 * Constructs a GoCell, initializes its name to "Go", and sets it as
	 * unavailable.
	 */
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	/**
	 * Executes the action associated with landing on the Go cell, currently
	 * implemented as a no-operation.
	 */
	public void playAction() {
	}

	/**
	 * Overrides the method to set the cell's name but is intentionally left empty
	 * to prevent changing the Go cell's name.
	 * 
	 * @param name The name to set, which is ignored in this implementation
	 */
	void setName(String name) {
	}
}
