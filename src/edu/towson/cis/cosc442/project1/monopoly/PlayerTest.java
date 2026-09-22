package edu.towson.cis.cosc442.project1.monopoly;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

	GameMaster gameMaster;
	
	/**
	 * Initializes the game master and sets up a simple game board with mock GUI for testing before each test.
	 * @throws Exception If initialization of the game master or setup fails.
	 */
	protected void setUp() throws Exception {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new SimpleGameBoard());
        gameMaster.setGUI(new MockGUI());
        gameMaster.setTestMode(true);
        gameMaster.reset();
	}
	
	/**
	 * Tests that a player can purchase a property and that ownership and money are updated accordingly.
	 */
	public void testPurchaseProperty() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.movePlayer(0, 3);
		Player player = gameMaster.getPlayer(0);
		player.purchase();
		assertEquals(1380, player.getMoney());
		assertEquals("Blue 3", player.getProperty(0).getName());
		PropertyCell cell =
			(PropertyCell) gameMaster.getGameBoard().queryCell("Blue 3");
		assertSame(player, cell.getTheOwner());
	}

	/**
	 * Verifies that multiple new players start at the same initial 'Go' cell on the game board.
	 */
	public void testSameGoCell() {
		GameBoard gameboard = gameMaster.getGameBoard();
		Player player1 = new Player();
		Player player2 = new Player();
		Cell go = gameboard.queryCell("Go");
		assertSame(go, player1.getPosition());
		assertSame(go, player2.getPosition());
	}
	
	/**
	 * Tests the rent payment process between two players, ensuring bankrupt status and money balances are handled correctly.
	 */
	public void testPayRentTo() {
		gameMaster.setNumberOfPlayers(2);
		gameMaster.movePlayer(0,4);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(1,4);
		gameMaster.btnEndTurnClicked();
		assertTrue(gameMaster.getPlayer(1).isBankrupt());
		assertEquals(2800, gameMaster.getPlayer(0).getMoney());
	}
	
	/**
	 * Tests exchanging properties between the current player and another player, verifying property count after exchange.
	 */
	public void testExchangeProperty() {
		gameMaster.setNumberOfPlayers(2);
		gameMaster.movePlayer(0,3);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.getPlayer(0).exchangeProperty(gameMaster.getPlayer(1));
		assertEquals(1,gameMaster.getCurrentPlayer().getPropertyNumber());
	}
	
	/**
	 * Tests the purchase of houses by a player on monopolized properties, validating monopolies and updated player funds.
	 */
	public void testPurchaseHouse() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.startGame();
		gameMaster.movePlayer(gameMaster.getCurrentPlayerIndex(),1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		gameMaster.btnEndTurnClicked();
		gameMaster.getCurrentPlayer().purchaseHouse("blue",2);
		assertEquals("blue", gameMaster.getCurrentPlayer().getMonopolies()[0]);
		assertEquals(880, gameMaster.getCurrentPlayer().getMoney());
	}
	
	/**
	 * Tests resetting a player's properties to ensure all properties are cleared properly.
	 */
	public void testResetProperty() {
		gameMaster.setNumberOfPlayers(1);
		gameMaster.movePlayer(0,1);
		gameMaster.getCurrentPlayer().purchase();
		assertEquals(gameMaster.getGameBoard().getCell(1), gameMaster.getCurrentPlayer().getAllProperties()[0]);
		gameMaster.getCurrentPlayer().resetProperty();
		assertEquals(0,gameMaster.getCurrentPlayer().getAllProperties().length);
	}
}
