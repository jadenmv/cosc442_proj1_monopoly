package edu.towson.cis.cosc442.project1.monopoly;

public class TradeDeal {
    private int amount;
    private int playerIndex;
    private String propertyName;

    /**
     * Returns the amount of money involved in the trade deal.
     * @return the amount of money offered in the trade
     */
    public int getAmount() {
        return amount;
    }
    
    /**
     * Returns the index of the player who is selling the property.
     * @return the player index of the seller
     */
    public int getPlayerIndex() {
        return playerIndex;
    }
    
    /**
     * Returns the name of the property involved in the trade deal.
     * @return the name of the property to be traded
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Constructs and returns a message describing the trade offer including buyer, property, seller, and amount.
     * @return a string message detailing the trade offer
     */
    public String makeMessage() {
        GameMaster instance = GameMaster.instance();
        return instance.getCurrentPlayer() + 
        	" wishes to purchase " +
        	propertyName + " from " + 
        	instance.getPlayer(playerIndex) +
        	" for " + amount + ".  " + 
        	instance.getPlayer(playerIndex) +
        	", do you wish to trade your property?";
    }
    
    /**
     * Sets the amount of money to be offered in the trade deal.
     * @param amount the amount of money to set for the trade
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * Sets the name of the property to be included in the trade deal.
     * @param propertyName the property name to set for the trade
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * Sets the index of the player who is selling the property in the trade deal.
     * @param playerIndex the player index to set as the seller
     */
    public void setSellerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
