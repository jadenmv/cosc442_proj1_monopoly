package edu.towson.cis.cosc442.project1.monopoly;

public class MockGUI implements MonopolyGUI {
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;
    private boolean[] btnTradeState = new boolean[2];

    /**
     * Enables the end turn button for the specified player index.
     * @param playerIndex the index of the player for whom to enable the end turn button
     */
    public void enableEndTurnBtn(int playerIndex) {
    }

    /**
     * Enables and sets up the GUI for the specified player's turn.
     * @param playerIndex the index of the player whose turn is being enabled
     */
    public void enablePlayerTurn(int playerIndex) {
    }

    /**
     * Enables the property purchase button for the specified player index.
     * @param playerIndex the index of the player for whom to enable the purchase button
     */
    public void enablePurchaseBtn(int playerIndex) {
    }
	/**
	 * Returns a fixed dice roll array representing the outcome of rolling two dice.
	 * @return an integer array of length two representing dice roll values
	 */
	public int[] getDiceRoll() {
		int roll[] = new int[2];
		roll[0] = 2;
		roll[1] = 3;
		return roll;
	}

    /**
     * Checks if the draw card button is currently enabled.
     * @return true if the draw card button is enabled; false otherwise
     */
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    /**
     * Checks if the end turn button is currently enabled.
     * @return true if the end turn button is enabled; false otherwise
     */
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }
	
	/**
	 * Checks if the get out of jail button is currently enabled.
	 * @return true if the get out of jail button is enabled; false otherwise
	 */
	public boolean isGetOutOfJailButtonEnabled() {
		return btnGetOutOfJailState;
	}

    /**
     * Checks if the trade button is enabled for the specified player index.
     * @param i the index of the player whose trade button state to check
     * @return true if the trade button for the player is enabled; false otherwise
     */
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
    }

    /**
     * Moves the specified player from one position to another on the game board.
     * @param index the index of the player to move
     * @param from the starting position index of the player
     * @param to the target position index to move the player to
     */
    public void movePlayer(int index, int from, int to) {
    }

    /**
     * Opens a respond dialog for the given trade deal.
     * @param deal the trade deal to respond to
     * @return a RespondDialog instance for the trade deal
     */
    public RespondDialog openRespondDialog(TradeDeal deal) {
        RespondDialog dialog = new MockRespondDialog(deal);
        return dialog;
    }

    /**
     * Opens a new trade dialog for initiating trade offers.
     * @return a TradeDialog instance for creating a trade
     */
    public TradeDialog openTradeDialog() {
        TradeDialog dialog = new MockTradeDialog();
        return dialog;
    }

    /**
     * Sets whether the buy house option is enabled in the GUI.
     * @param b true to enable buying houses, false to disable
     */
    public void setBuyHouseEnabled(boolean b) {
    }

    /**
     * Enables or disables the draw card button in the GUI.
     * @param b true to enable the draw card button, false to disable
     */
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    /**
     * Enables or disables the end turn button in the GUI.
     * @param enabled true to enable the end turn button, false to disable
     */
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    /**
     * Enables or disables the get out of jail button in the GUI.
     * @param b true to enable the get out of jail button, false to disable
     */
    public void setGetOutOfJailEnabled(boolean b) {
    	this.btnGetOutOfJailState = b;
    }

    /**
     * Enables or disables the purchase property button in the GUI.
     * @param enabled true to enable the purchase property button, false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
    }

    /**
     * Enables or disables the roll dice button in the GUI.
     * @param b true to enable the roll dice button, false to disable
     */
    public void setRollDiceEnabled(boolean b) {
    }

    /**
     * Sets whether the trade button is enabled for the specified player index.
     * @param index the index of the player whose trade button state to set
     * @param b true to enable, false to disable the trade button
     */
    public void setTradeEnabled(int index, boolean b) {
        this.btnTradeState[index] = b;
    }

    /**
     * Displays the buy house dialog for the current player.
     * @param currentPlayer the player currently taking turn who may buy houses
     */
    public void showBuyHouseDialog(Player currentPlayer) {
    }

    /**
     * Displays a message to the user through the GUI.
     * @param string the message text to display
     */
    public void showMessage(String string) {
    }

	/**
	 * Simulates and returns a fixed utility dice roll value.
	 * @return an integer representing the utility dice roll sum
	 */
	public int showUtilDiceRoll() {
//		int[] diceValues = GameMaster.instance().rollDice();
//		return diceValues[0] + diceValues[1];
		return 10;
	}

    /**
     * Starts and initializes the game GUI and related components.
     */
    public void startGame() {
    }

	/**
	 * Updates the GUI state, typically to reflect changes in the game.
	 */
	public void update() {
	}
}
