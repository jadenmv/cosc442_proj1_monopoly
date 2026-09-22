package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class MainWindow extends JFrame implements MonopolyGUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel eastPanel = new JPanel();
	ArrayList<GUICell> guiCells = new ArrayList<GUICell>();

	JPanel northPanel = new JPanel();
	PlayerPanel[] playerPanels;
	JPanel southPanel = new JPanel();
	JPanel westPanel = new JPanel();

	/**
	 * Constructs the main game window, sets its layout, borders, size to full
	 * screen, and adds a window listener to handle closing.
	 */
	public MainWindow() {
		northPanel.setBorder(new LineBorder(Color.BLACK));
		southPanel.setBorder(new LineBorder(Color.BLACK));
		westPanel.setBorder(new LineBorder(Color.BLACK));
		eastPanel.setBorder(new LineBorder(Color.BLACK));

		Container c = getContentPane();
		// setSize(800, 600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setSize(d);
		c.add(northPanel, BorderLayout.NORTH);
		c.add(southPanel, BorderLayout.SOUTH);
		c.add(eastPanel, BorderLayout.EAST);
		c.add(westPanel, BorderLayout.WEST);

		this.addWindowListener(new WindowAdapter() {
			/**
			 * Handles the window closing event by exiting the application.
			 * 
			 * @param e TODO
			 */
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * Adds GUI cell components representing game cells to the specified panel and
	 * tracks them internally.
	 * 
	 * @param panel the panel to which the GUI cells will be added
	 * @param cells the list of game cells to be represented
	 */
	private void addCells(JPanel panel, List<?> cells) {
		for (int x = 0; x < cells.size(); x++) {
			GUICell cell = new GUICell((Cell) cells.get(x));
			panel.add(cell);
			guiCells.add(cell);
		}
	}

	/**
	 * Creates and adds PlayerPanel components to display each player's information
	 * in the GUI center area.
	 */
	private void buildPlayerPanels() {
		GameMaster master = GameMaster.instance();
		JPanel infoPanel = new JPanel();
		int players = master.getNumberOfPlayers();
		infoPanel.setLayout(new GridLayout(2, (players + 1) / 2));
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		playerPanels = new PlayerPanel[master.getNumberOfPlayers()];
		for (int i = 0; i < master.getNumberOfPlayers(); i++) {
			playerPanels[i] = new PlayerPanel(master.getPlayer(i));
			infoPanel.add(playerPanels[i]);
			playerPanels[i].displayInfo();
		}
	}

	/**
	 * Enables the 'End Turn' button for the specified player.
	 * 
	 * @param playerIndex the index of the player whose button will be enabled
	 */
	public void enableEndTurnBtn(int playerIndex) {
		playerPanels[playerIndex].setEndTurnEnabled(true);
	}

	/**
	 * Enables the 'Roll Dice' button for the specified player, indicating it is
	 * their turn.
	 * 
	 * @param playerIndex the index of the player to enable rolling dice
	 */
	public void enablePlayerTurn(int playerIndex) {
		playerPanels[playerIndex].setRollDiceEnabled(true);

	}

	/**
	 * Enables the 'Purchase Property' button for the specified player.
	 * 
	 * @param playerIndex the index of the player whose purchase button will be
	 *                    enabled
	 */
	public void enablePurchaseBtn(int playerIndex) {
		playerPanels[playerIndex].setPurchasePropertyEnabled(true);
	}

	@SuppressWarnings("deprecation")
	/**
	 * Displays a dialog for manual dice roll input and returns the result as an
	 * integer array.
	 * 
	 * @return an integer array representing the dice roll values
	 */
	public int[] getDiceRoll() {
		TestDiceRollDialog dialog = new TestDiceRollDialog(this);
		dialog.show();
		return dialog.getDiceRoll();
	}

	/**
	 * Checks if the 'Draw Card' button is enabled for the current player.
	 * 
	 * @return true if enabled, false otherwise
	 */
	public boolean isDrawCardButtonEnabled() {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		return playerPanels[currentPlayerIndex].isDrawCardButtonEnabled();
	}

	/**
	 * Checks if the 'End Turn' button is enabled for the current player.
	 * 
	 * @return true if enabled, false otherwise
	 */
	public boolean isEndTurnButtonEnabled() {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		return playerPanels[currentPlayerIndex].isEndTurnButtonEnabled();
	}

	/**
	 * Checks if the 'Get Out of Jail' button is enabled for the current player.
	 * 
	 * @return true if enabled, false otherwise
	 */
	public boolean isGetOutOfJailButtonEnabled() {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		return playerPanels[currentPlayerIndex].isGetOutOfJailButtonEnabled();
	}

	/**
	 * Checks if the 'Trade' button is enabled for the specified player.
	 * 
	 * @param i the index of the player to check
	 * @return true if enabled, false otherwise
	 */
	public boolean isTradeButtonEnabled(int i) {
		return playerPanels[i].isTradeButtonEnabled();
	}

	/**
	 * Moves a player graphically from one cell to another on the game board.
	 * 
	 * @param index the player index
	 * @param from  the cell index to move from
	 * @param to    the cell index to move to
	 */
	public void movePlayer(int index, int from, int to) {
		GUICell fromCell = queryCell(from);
		GUICell toCell = queryCell(to);
		fromCell.removePlayer(index);
		toCell.addPlayer(index);
	}

	@SuppressWarnings("deprecation")
	/**
	 * Opens a dialog allowing a player to respond to a trade deal and returns the
	 * dialog instance.
	 * 
	 * @param deal the trade deal to respond to
	 * @return the RespondDialog object for the trade response
	 */
	public RespondDialog openRespondDialog(TradeDeal deal) {
		GUIRespondDialog dialog = new GUIRespondDialog();
		dialog.setDeal(deal);
		dialog.show();
		return dialog;
	}

	@SuppressWarnings("deprecation")
	/**
	 * Opens the trade dialog where players can propose trades and returns the
	 * dialog instance.
	 * 
	 * @return the TradeDialog object for proposing trades
	 */
	public TradeDialog openTradeDialog() {
		GUITradeDialog dialog = new GUITradeDialog(this);
		dialog.show();
		return dialog;
	}

	/**
	 * Finds and returns the GUI cell corresponding to the given board cell index.
	 * 
	 * @param index the index of the cell to query
	 * @return the GUICell matching the board cell index, or null if none found
	 */
	private GUICell queryCell(int index) {
		Cell cell = GameMaster.instance().getGameBoard().getCell(index);
		for (int x = 0; x < guiCells.size(); x++) {
			GUICell guiCell = (GUICell) guiCells.get(x);
			if (guiCell.getCell() == cell)
				return guiCell;
		}
		return null;
	}

	/**
	 * Sets the 'Buy House' button enabled state for the current player.
	 * 
	 * @param b true to enable, false to disable
	 */
	public void setBuyHouseEnabled(boolean b) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setBuyHouseEnabled(b);
	}

	/**
	 * Sets the 'Draw Card' button enabled state for the current player.
	 * 
	 * @param b true to enable, false to disable
	 */
	public void setDrawCardEnabled(boolean b) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setDrawCardEnabled(b);
	}

	/**
	 * Sets the 'End Turn' button enabled state for the current player.
	 * 
	 * @param enabled true to enable, false to disable
	 */
	public void setEndTurnEnabled(boolean enabled) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setEndTurnEnabled(enabled);
	}

	/**
	 * Sets the 'Get Out of Jail' button enabled state for the current player.
	 * 
	 * @param b true to enable, false to disable
	 */
	public void setGetOutOfJailEnabled(boolean b) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setGetOutOfJailEnabled(b);
	}

	/**
	 * Sets the 'Purchase Property' button enabled state for the current player.
	 * 
	 * @param enabled true to enable, false to disable
	 */
	public void setPurchasePropertyEnabled(boolean enabled) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setPurchasePropertyEnabled(enabled);
	}

	/**
	 * Sets the 'Roll Dice' button enabled state for the current player.
	 * 
	 * @param b true to enable, false to disable
	 */
	public void setRollDiceEnabled(boolean b) {
		int currentPlayerIndex = GameMaster.instance().getCurrentPlayerIndex();
		playerPanels[currentPlayerIndex].setRollDiceEnabled(b);
	}

	/**
	 * Sets the 'Trade' button enabled state for the specified player.
	 * 
	 * @param index the index of the player
	 * @param b     true to enable, false to disable
	 */
	public void setTradeEnabled(int index, boolean b) {
		playerPanels[index].setTradeEnabled(b);
	}

	/**
	 * Sets up the game board GUI panels with the cells arranged appropriately and
	 * initializes player panels.
	 * 
	 * @param board the GameBoard containing board cells to display
	 */
	public void setupGameBoard(GameBoard board) {
		Dimension dimension = GameBoardUtil.calculateDimension(board.getCellNumber());
		northPanel.setLayout(new GridLayout(1, dimension.width + 2));
		southPanel.setLayout(new GridLayout(1, dimension.width + 2));
		westPanel.setLayout(new GridLayout(dimension.height, 1));
		eastPanel.setLayout(new GridLayout(dimension.height, 1));
		addCells(northPanel, GameBoardUtil.getNorthCells(board));
		addCells(southPanel, GameBoardUtil.getSouthCells(board));
		addCells(eastPanel, GameBoardUtil.getEastCells(board));
		addCells(westPanel, GameBoardUtil.getWestCells(board));
		buildPlayerPanels();
	}

	@SuppressWarnings("deprecation")
	/**
	 * Displays the dialog for a player to buy houses on their properties.
	 * 
	 * @param currentPlayer the player who may buy houses
	 */
	public void showBuyHouseDialog(Player currentPlayer) {
		BuyHouseDialog dialog = new BuyHouseDialog(currentPlayer);
		dialog.show();
	}

	/**
	 * Displays a message dialog containing the given string.
	 * 
	 * @param msg the message to display
	 */
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	/**
	 * Shows the utility dice roll dialog and returns the dice roll result.
	 * 
	 * @return the result of the utility dice roll as an integer
	 */
	public int showUtilDiceRoll() {
		return UtilDiceRoll.showDialog();
	}

	/**
	 * Initializes the game by placing all players at the starting cell visually.
	 */
	public void startGame() {
		int numberOfPlayers = GameMaster.instance().getNumberOfPlayers();
		for (int i = 0; i < numberOfPlayers; i++) {
			movePlayer(i, 0, 0);
		}
	}

	/**
	 * Updates the display of all player panels and GUI board cells to reflect
	 * current game state.
	 */
	public void update() {
		for (int i = 0; i < playerPanels.length; i++) {
			playerPanels[i].displayInfo();
		}
		for (int j = 0; j < guiCells.size(); j++) {
			GUICell cell = (GUICell) guiCells.get(j);
			cell.displayInfo();
		}
	}
}
