package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Cell {
	private boolean available = true;
	private String name;
	protected Player theOwner;

	/**
	 * Returns the name of this cell.
	 * @return The name of the cell as a String.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player who owns this cell.
	 * @return The Player who owns the cell, or null if none.
	 */
	public Player getTheOwner() {
		return theOwner;
	}
	
	/**
	 * Returns the price of this cell, defaulting to zero.
	 * @return The price of the cell as an integer.
	 */
	public int getPrice() {
		return 0;
	}

	/**
	 * Checks if this cell is currently available.
	 * @return True if the cell is available; false otherwise.
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Executes the specific action associated with this cell.
	 */
	public abstract void playAction();

	/**
	 * Sets the availability status of this cell.
	 * @param available The new availability status to set.
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Assigns a name to this cell.
	 * @param name The name to assign to the cell.
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Assigns an owner to this cell.
	 * @param owner The Player to set as the owner.
	 */
	public void setTheOwner(Player owner) {
		this.theOwner = owner;
	}
    
    /**
     * Returns the string representation of this cell.
     * @return The name of the cell as a String.
     */
    public String toString() {
        return name;
    }
}
