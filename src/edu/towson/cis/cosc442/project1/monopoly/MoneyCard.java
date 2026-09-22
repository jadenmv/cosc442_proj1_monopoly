package edu.towson.cis.cosc442.project1.monopoly;

public class MoneyCard extends Card {
    private int amount;
    private int cardType;
    
    private String label;
    
    /**
     * Constructs a MoneyCard with a label, an amount, and a card type.
     * @param label the descriptive label for this MoneyCard
     * @param amount the monetary amount associated with this card
     * @param cardType the integer representing the type/category of this card
     */
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    /**
     * Applies the card's action by adding the amount to the current player's money.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    /**
     * Returns the integer representing the card's type.
     * @return the card type as an integer
     */
    public int getCardType() {
        return cardType;
    }

    /**
     * Returns the label describing this MoneyCard.
     * @return the label string of this card
     */
    public String getLabel() {
        return label;
    }
}
