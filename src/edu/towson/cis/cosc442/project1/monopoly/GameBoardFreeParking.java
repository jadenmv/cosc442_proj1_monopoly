
package edu.towson.cis.cosc442.project1.monopoly;

public class GameBoardFreeParking extends GameBoard {
	/**
	 * Constructs a GameBoardFreeParking instance initializing it with Jail, Free
	 * Parking, and Go To Jail cells.
	 */
	public GameBoardFreeParking() {
		super();
		JailCell jail = new JailCell();
		FreeParkingCell freeParking = new FreeParkingCell();
		GoToJailCell goToJail = new GoToJailCell();
		addCell(jail);
		addCell(freeParking);
		addCell(goToJail);

	}
}
