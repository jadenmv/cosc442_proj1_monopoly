package edu.towson.cis.cosc442.project1.monopoly;

public class FreeParkingCell extends Cell {

	/**
	 * Constructs a FreeParkingCell and sets its name to "Free Parking".
	 */
	public FreeParkingCell() {
		setName("Free Parking");
	}

	/**
	 * Performs no action when a player lands on Free Parking.
	 */
	public void playAction() {
		return;
	}
}
