package edu.towson.cis.cosc442.project1.monopoly;

public abstract class Card {

    public static int TYPE_CHANCE = 1;
    public static int TYPE_CC = 2;

    /**
     * Returns the textual label or description of the card.
     * 
     * @return the label or description of this card
     */
    public abstract String getLabel();

    /**
     * Executes the action associated with this card.
     */
    public abstract void applyAction();

    /**
     * Returns the type of the card represented as an integer constant.
     * 
     * @return the integer code representing the card type
     */
    public abstract int getCardType();
}
