package edu.towson.cis.cosc442.project1.monopoly;

public class RailRoadCell extends Cell {
	static private int baseRent;
	static public String COLOR_GROUP = "RAILROAD";
	static private int price;

	/**
	 * Sets the base rent amount for all railroad cells.
	 * @param baseRent the base rent amount to be set
	 */
	public static void setBaseRent(int baseRent) {
		RailRoadCell.baseRent = baseRent;
	}

	/**
	 * Sets the purchase price for all railroad cells.
	 * @param price the price amount to be set
	 */
	public static void setPrice(int price) {
		RailRoadCell.price = price;
	}
	
	/**
	 * Returns the current purchase price of the railroad cell.
	 * @return the purchase price of the railroad cell
	 */
	public int getPrice() {
		return RailRoadCell.price;
	}

	/**
	 * Calculates and returns the rent owed based on the number of railroads owned.
	 * @return the calculated rent amount for this railroad cell
	 */
	public int getRent() {
		return RailRoadCell.baseRent * (int)Math.pow(2, theOwner.numberOfRR() - 1);
	}
	
	/**
	 * Executes the action for the current player when landing on the railroad cell, including rent payment if owned by another player.
	 */
	public void playAction() {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
	}
}
