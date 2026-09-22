package edu.towson.cis.cosc442.project1.monopoly;


public class JailCard extends Card {
    int type;
    
    /**
     * Constructs a JailCard with the specified card type identifier.
     * @param cardType the integer identifier representing the type of JailCard
     */
    public JailCard(int cardType) {
        type = cardType;
    }

    /**
     * Applies the effect of the JailCard by sending the current player to jail.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		GameMaster.instance().getGameBoard().queryCell("Jail");
		GameMaster.instance().sendToJail(currentPlayer);
    }

    /**
     * Returns the integer type identifier of this JailCard.
     * @return the type identifier of the JailCard
     */
    public int getCardType() {
        return type;
    }

    /**
     * Returns the descriptive label of the JailCard's effect.
     * @return a string describing the JailCard's action
     */
    public String getLabel() {
        return "Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell";
    }
}
