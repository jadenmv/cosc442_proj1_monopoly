package edu.towson.cis.cosc442.project1.monopoly;

import junit.framework.TestCase;

public class MovePlayerCardTest extends TestCase {
    GameMaster gameMaster;
    Card movePlayerCard;
    
    /**
     * Initializes the game state with one player, a custom game board, a mock GUI, and adds a specific MovePlayerCard for testing.
     */
    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCMovePlayer());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		movePlayerCard = new MovePlayerCard("Blue 1", Card.TYPE_CC);
		gameMaster.getGameBoard().addCard(movePlayerCard);
    }
    
    /**
     * Verifies that the MovePlayerCard label correctly describes the destination cell.
     */
    public void testJailCardLabel() {
        assertEquals("Go to Blue 1", movePlayerCard.getLabel());
    }
    
    /**
     * Tests that drawing the MovePlayerCard moves the current player to the correct board cell.
     */
    public void testMovePlayerCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(movePlayerCard, card);
		card.applyAction();
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
    }
    
    /**
     * Checks that UI buttons enable and disable correctly when the MovePlayerCard is drawn and the player is moved, and verifies the player's position and money after the move.
     */
    public void testMovePlayerCardUI() {
        gameMaster.movePlayer(0, 2);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Blue 1"), cell);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
		assertEquals(1700, gameMaster.getCurrentPlayer().getMoney());
    }
}
