package edu.towson.cis.cosc442.project1.monopoly;

public class PropertyCell extends Cell {
	private String colorGroup;
	private int housePrice;
	private int numHouses;
	private int rent;
	private int sellPrice;

	/**
	 * Returns the color group of this property.
	 * @return The color group as a string.
	 */
	public String getColorGroup() {
		return colorGroup;
	}

	/**
	 * Retrieves the price for building a house on this property.
	 * @return The house price as an integer.
	 */
	public int getHousePrice() {
		return housePrice;
	}

	/**
	 * Gets the current number of houses on this property.
	 * @return The number of houses as an integer.
	 */
	public int getNumHouses() {
		return numHouses;
	}
    
    /**
     * Returns the selling price of this property.
     * @return The selling price as an integer.
     */
    public int getPrice() {
		return sellPrice;
	}

	/**
	 * Calculates and returns the rent owed for this property, factoring in houses and monopolies.
	 * @return The rent amount as an integer.
	 */
	public int getRent() {
		int rentToCharge = rent;
		String [] monopolies = theOwner.getMonopolies();
		rentToCharge = calculateMonopoliesRent(rentToCharge, monopolies);
		if(numHouses > 0) {
			rentToCharge = rent * (numHouses + 1);
		}
		return rentToCharge;
	}

	/**
	 * Calculates adjusted rent if this property is part of a monopoly group.
	 * @param rentToCharge The current rent amount to be possibly adjusted.
	 * @param monopolies Array of color groups that the owner has monopolies in.
	 * @return The adjusted rent amount as an integer.
	 */
	private int calculateMonopoliesRent(int rentToCharge, String[] monopolies) {
		for(int i = 0; i < monopolies.length; i++) {
			if(monopolies[i].equals(colorGroup)) {
				rentToCharge = rent * 2;
			}
		}
		return rentToCharge;
	}

	/**
	 * Executes the action to be taken when a player lands on this property, including rent payment if owned by another player.
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

	/**
	 * Sets the color group of this property.
	 * @param colorGroup The color group to assign.
	 */
	public void setColorGroup(String colorGroup) {
		this.colorGroup = colorGroup;
	}

	/**
	 * Sets the price for building a house on this property.
	 * @param housePrice The house price to assign.
	 */
	public void setHousePrice(int housePrice) {
		this.housePrice = housePrice;
	}

	/**
	 * Sets the number of houses currently on this property.
	 * @param numHouses The number of houses to set.
	 */
	public void setNumHouses(int numHouses) {
		this.numHouses = numHouses;
	}

	/**
	 * Sets the selling price of this property.
	 * @param sellPrice The selling price to assign.
	 */
	public void setPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	/**
	 * Sets the base rent amount for this property.
	 * @param rent The rent amount to assign.
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}
}
