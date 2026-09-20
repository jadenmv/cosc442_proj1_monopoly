package edu.towson.cis.cosc442.project1.monopoly;


public class MovePlayerCard extends Card {
    
    private String destination;
    private int type;

    /**
     * Constructs a MovePlayerCard with a specified destination and card type.
     * @param destination the name of the destination cell to move the player to
     * @param cardType the type identifier of the card
     */
    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    /**
     * Executes the card's action by moving the current player to the specified destination cell on the game board.
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = GameMaster.instance().getGameBoard().queryCellIndex(destination);
        int currentCell = GameMaster.instance().getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if(currentCell > newCell) {
            diceValue = (GameMaster.instance().getGameBoard().getCellNumber() + 
                (newCell - currentCell));
        }
        else {
            diceValue = newCell - currentCell;
        }
        System.out.println(diceValue);
        GameMaster.instance().movePlayer(currentPlayer, diceValue);
    }

    /**
     * Retrieves the integer type identifier of this MovePlayerCard.
     * @return the card's type as an integer
     */
    public int getCardType() {
        return type;
    }

    /**
     * Returns a label string describing the card's action to go to the destination.
     * @return a string label in the form 'Go to [destination]'
     */
    public String getLabel() {
        return "Go to " + destination;
    }

}
