package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class Player {
	//the key of colorGroups is the name of the color group.
	private Hashtable<String, Integer> colorGroups = new Hashtable<String, Integer>();
	private boolean inJail;
	private int money;
	private String name;

	private Cell position;
	private ArrayList<PropertyCell> properties = new ArrayList<PropertyCell>();
	private ArrayList<Cell> railroads = new ArrayList<Cell>();
	private ArrayList<Cell> utilities = new ArrayList<Cell>();
	
	/**
	 * Constructs a player positioned at 'Go' with default settings.
	 */
	public Player() {
		GameBoard gb = GameMaster.instance().getGameBoard();
		inJail = false;
		if(gb != null) {
			position = gb.queryCell("Go");
		}
	}

    /**
     * Assigns ownership of the specified property to the player and deducts the purchase amount from the player's money.
     * @param property the property cell to be bought
     * @param amount the purchase price paid for the property
     */
    public void buyProperty(Cell property, int amount) {
        property.setTheOwner(this);
        if(property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell)property;
            properties.add(cell);
            colorGroups.put(
                    cell.getColorGroup(), 
                    new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
        }
        if(property instanceof RailRoadCell) {
            railroads.add(property);
            colorGroups.put(
                    RailRoadCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(RailRoadCell.COLOR_GROUP)+1));
        }
        if(property instanceof UtilityCell) {
            utilities.add(property);
            colorGroups.put(
                    UtilityCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(UtilityCell.COLOR_GROUP)+1));
        }
        setMoney(getMoney() - amount);
    }
	
	/**
	 * Determines if the player can buy a house by checking if the player owns any monopolies.
	 * @return true if the player owns one or more monopolies, false otherwise
	 */
	public boolean canBuyHouse() {
		return (getMonopolies().length != 0);
	}

	/**
	 * Checks whether the player owns a property with the specified name.
	 * @param property the name of the property to check
	 * @return true if the player owns the property, false otherwise
	 */
	public boolean checkProperty(String property) {
		for(int i=0;i<properties.size();i++) {
			Cell cell = (Cell)properties.get(i);
			if(cell.getName().equals(property)) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Transfers all properties owned by this player to another player or resets them if null is passed.
	 * @param player the player to receive the properties or null to reset ownership
	 */
	public void exchangeProperty(Player player) {
		for(int i = 0; i < getPropertyNumber(); i++ ) {
			PropertyCell cell = getProperty(i);
			cell.setTheOwner(player);
			if(player == null) {
				cell.setAvailable(true);
				cell.setNumHouses(0);
			}
			else {
				player.properties.add(cell);
				colorGroups.put(
						cell.getColorGroup(), 
						new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
			}
		}
		properties.clear();
	}
    
    /**
     * Returns all cells owned by the player including properties, railroads, and utilities.
     * @return an array of all cells owned by the player
     */
    public Cell[] getAllProperties() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        list.addAll(properties);
        list.addAll(utilities);
        list.addAll(railroads);
        return (Cell[])list.toArray(new Cell[list.size()]);
    }

	/**
	 * Retrieves the current amount of money the player has.
	 * @return the player's current money balance
	 */
	public int getMoney() {
		return this.money;
	}
	
	/**
	 * Obtains the list of color groups for which the player owns all properties, constituting monopolies.
	 * @return an array of monopoly color group names owned by the player
	 */
	public String[] getMonopolies() {
		ArrayList<String> monopolies = new ArrayList<String>();
		Enumeration<String> colors = colorGroups.keys();
		while(colors.hasMoreElements()) {
			String color = (String)colors.nextElement();
            if(!(color.equals(RailRoadCell.COLOR_GROUP)) && !(color.equals(UtilityCell.COLOR_GROUP))) {
    			Integer num = (Integer)colorGroups.get(color);
    			GameBoard gameBoard = GameMaster.instance().getGameBoard();
    			if(num.intValue() == gameBoard.getPropertyNumberForColor(color)) {
    				monopolies.add(color);
    			}
            }
		}
		return (String[])monopolies.toArray(new String[monopolies.size()]);
	}

	/**
	 * Returns the player's name.
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Processes the player paying bail and removes them from jail, handling bankruptcy if it occurs.
	 */
	public void getOutOfJail() {
		money -= JailCell.BAIL;
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(null);
		}
		inJail = false;
		GameMaster.instance().updateGUI();
	}

	/**
	 * Retrieves the current cell position of the player on the game board.
	 * @return the current cell occupied by the player
	 */
	public Cell getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the property owned by the player at the specified index.
	 * @param index the index of the property to retrieve
	 * @return the PropertyCell at the given index
	 */
	public PropertyCell getProperty(int index) {
		return (PropertyCell)properties.get(index);
	}
	
	/**
	 * Returns the total number of properties the player owns.
	 * @return the count of properties owned by the player
	 */
	public int getPropertyNumber() {
		return properties.size();
	}

	/**
	 * Gets the number of properties owned by the player in the specified color group.
	 * @param name the color group name
	 * @return the count of properties owned in the specified color group
	 */
	private int getPropertyNumberForColor(String name) {
		Integer number = (Integer)colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Checks whether the player has run out of money (bankrupt).
	 * @return true if the player's money is zero or less, false otherwise
	 */
	public boolean isBankrupt() {
		return money <= 0;
	}

	/**
	 * Indicates if the player is currently in jail.
	 * @return true if the player is in jail, false otherwise
	 */
	public boolean isInJail() {
		return inJail;
	}

	/**
	 * Returns the number of railroads the player owns.
	 * @return the count of railroads owned by the player
	 */
	public int numberOfRR() {
		return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
	}

	/**
	 * Returns the number of utility cells the player owns.
	 * @return the count of utilities owned by the player
	 */
	public int numberOfUtil() {
		return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
	}
	
	/**
	 * Pays rent to another player, updating money balances and handling bankruptcy if insufficient funds.
	 * @param owner the player to whom rent is paid
	 * @param rentValue the amount of rent to be paid
	 */
	public void payRentTo(Player owner, int rentValue) {
		if(money < rentValue) {
			owner.money += money;
			money -= rentValue;
		}
		else {
			money -= rentValue;
			owner.money +=rentValue;
		}
		if(isBankrupt()) {
			money = 0;
			exchangeProperty(owner);
		}
	}
	
	/**
	 * Purchases the property at the player's current position if it is available.
	 */
	public void purchase() {
		if(getPosition().isAvailable()) {
			Cell c = getPosition();
			c.setAvailable(false);
			if(c instanceof PropertyCell) {
				PropertyCell cell = (PropertyCell)c;
				purchaseProperty(cell);
			}
			if(c instanceof RailRoadCell) {
				RailRoadCell cell = (RailRoadCell)c;
				purchaseRailRoad(cell);
			}
			if(c instanceof UtilityCell) {
				UtilityCell cell = (UtilityCell)c;
				purchaseUtility(cell);
			}
		}
	}
	
	/**
	 * Purchases houses for all properties in the specified monopoly color group, if affordable and within house limit.
	 * @param selectedMonopoly the monopoly color group to build houses on
	 * @param houses the number of houses to purchase per property
	 */
	public void purchaseHouse(String selectedMonopoly, int houses) {
		GameBoard gb = GameMaster.instance().getGameBoard();
		PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
		if((money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
			for(int i = 0; i < cells.length; i++) {
				int newNumber = cells[i].getNumHouses() + houses;
				if (newNumber <= 5) {
					cells[i].setNumHouses(newNumber);
					this.setMoney(money - (cells[i].getHousePrice() * houses));
					GameMaster.instance().updateGUI();
				}
			}
		}
	}
	
	/**
	 * Buys a specific property cell by paying its price.
	 * @param cell the property cell to purchase
	 */
	private void purchaseProperty(PropertyCell cell) {
        buyProperty(cell, cell.getPrice());
	}

	/**
	 * Buys a railroad cell by paying its price.
	 * @param cell the railroad cell to purchase
	 */
	private void purchaseRailRoad(RailRoadCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

	/**
	 * Buys a utility cell by paying its price.
	 * @param cell the utility cell to purchase
	 */
	private void purchaseUtility(UtilityCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

    /**
     * Sells a specified property and credits the player with the sale amount.
     * @param property the property cell to sell
     * @param amount the amount received for selling the property
     */
    public void sellProperty(Cell property, int amount) {
        property.setTheOwner(null);
        if(property instanceof PropertyCell) {
            properties.remove(property);
        }
        if(property instanceof RailRoadCell) {
            railroads.remove(property);
        }
        if(property instanceof UtilityCell) {
            utilities.remove(property);
        }
        setMoney(getMoney() + amount);
    }

	/**
	 * Sets the player's jail status to the specified value.
	 * @param inJail true to put player in jail, false to release
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/**
	 * Updates the player's money balance to the specified amount.
	 * @param money the new amount of money for the player
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * Sets the player's name.
	 * @param name the new name for the player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Updates the player's position on the game board to the specified cell.
	 * @param newPosition the cell to set as the player's new position
	 */
	public void setPosition(Cell newPosition) {
		this.position = newPosition;
	}

    /**
     * Returns the player's name as its string representation.
     * @return the player's name
     */
    public String toString() {
        return name;
    }
    
    /**
     * Clears all properties, railroads, and utilities owned by the player.
     */
    public void resetProperty() {
    	properties = new ArrayList<PropertyCell>();
    	railroads = new ArrayList<Cell>();
    	utilities = new ArrayList<Cell>();
	}
}
