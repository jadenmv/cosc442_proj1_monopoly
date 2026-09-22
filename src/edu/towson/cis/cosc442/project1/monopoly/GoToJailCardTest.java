package edu.towson.cis.cosc442.project1.monopoly;

import junit.framework.TestCase;

public class GoToJailCardTest extends TestCase {
    GameMaster gameMaster;
    Card jailCard = new JailCard(Card.TYPE_CC);
    
    /**
     * Initializes the game environment for each test by setting up the game master, game board, player count, GUI, and adding the jail card.
     */
    protected void setUp() {
		gameMaster = GameMaster.instance();
		gameMaster.setGameBoard(new GameBoardCCJail());
		gameMaster.setNumberOfPlayers(1);
		gameMaster.reset();
		gameMaster.setGUI(new MockGUI());
		gameMaster.getGameBoard().addCard(jailCard);
    }
    
    /**
     * Verifies that drawing and applying the jail card correctly moves the player to the Jail cell.
     */
    public void testJailCardAction() {
		Card card = gameMaster.drawCCCard();
		assertEquals(jailCard, card);
		card.applyAction();
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
    }
    
    /**
     * Checks that the jail card's label matches the expected description of its effect.
     */
    public void testJailCardLabel() {
        assertEquals("Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell", jailCard.getLabel());
    }
    
    /**
     * Tests the GUI behavior when drawing the jail card, including button states and player position update.
     */
    public void testJailCardUI() {
        gameMaster.movePlayer(0, 1);
        assertTrue(gameMaster.getGUI().isDrawCardButtonEnabled());
        assertFalse(gameMaster.getGUI().isEndTurnButtonEnabled());
        gameMaster.btnDrawCardClicked();
        assertFalse(gameMaster.getGUI().isDrawCardButtonEnabled());
		Cell cell = gameMaster.getCurrentPlayer().getPosition();
		assertEquals(gameMaster.getGameBoard().queryCell("Jail"), cell);
		assertTrue(gameMaster.getGUI().isEndTurnButtonEnabled());
    }
}
