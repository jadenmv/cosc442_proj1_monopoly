package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.border.BevelBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class PlayerPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JButton btnBuyHouse;
    private JButton btnDrawCard;
    private JButton btnEndTurn;
    private JButton btnGetOutOfJail;
    private JButton btnPurchaseProperty;
    private JButton btnRollDice;
    private JButton btnTrade;

    private JLabel lblMoney;
    private JLabel lblName;

    private Player player;

    private JTextArea txtProperty;

    /**
     * Constructs a PlayerPanel GUI component for the specified player with
     * initialized controls and event handlers.
     * 
     * @param player the Player object for whom this panel is created
     */
    public PlayerPanel(Player player) {
        JPanel pnlAction = new JPanel();
        JPanel pnlInfo = new JPanel();
        btnRollDice = new JButton("Roll Dice");
        btnPurchaseProperty = new JButton("Purchase Property");
        btnEndTurn = new JButton("End Turn");
        btnBuyHouse = new JButton("Buy House");
        btnGetOutOfJail = new JButton("Get Out of Jail");
        btnDrawCard = new JButton("Draw Card");
        btnTrade = new JButton("Trade");
        this.player = player;
        lblName = new JLabel();
        lblMoney = new JLabel();
        txtProperty = new JTextArea(30, 70);

        txtProperty.setEnabled(false);

        JPanel pnlName = new JPanel();
        JPanel pnlProperties = new JPanel();

        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.add(pnlName, BorderLayout.NORTH);
        pnlInfo.add(pnlProperties, BorderLayout.CENTER);

        pnlProperties.setLayout(new OverlayLayout(pnlProperties));

        pnlName.add(lblName);
        pnlName.add(lblMoney);
        pnlProperties.add(txtProperty);

        pnlAction.setLayout(new GridLayout(3, 3));
        pnlAction.add(btnBuyHouse);
        pnlAction.add(btnRollDice);
        pnlAction.add(btnPurchaseProperty);
        pnlAction.add(btnGetOutOfJail);
        pnlAction.add(btnEndTurn);
        pnlAction.add(btnDrawCard);
        pnlAction.add(btnTrade);

        pnlAction.doLayout();
        pnlInfo.doLayout();
        pnlName.doLayout();
        pnlProperties.doLayout();
        this.doLayout();

        setLayout(new BorderLayout());
        add(pnlInfo, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.SOUTH);

        btnRollDice.setEnabled(false);
        btnPurchaseProperty.setEnabled(false);
        btnEndTurn.setEnabled(false);
        btnBuyHouse.setEnabled(false);
        btnGetOutOfJail.setEnabled(false);
        btnDrawCard.setEnabled(false);
        btnTrade.setEnabled(false);

        setBorder(new BevelBorder(BevelBorder.RAISED));

        btnRollDice.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnRollDiceClicked();
            }
        });

        btnEndTurn.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnEndTurnClicked();
            }
        });

        btnPurchaseProperty.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnPurchasePropertyClicked();
            }
        });

        btnBuyHouse.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnBuyHouseClicked();
            }
        });

        btnGetOutOfJail.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnGetOutOfJailClicked();
            }
        });

        btnDrawCard.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                Card card = GameMaster.instance().btnDrawCardClicked();
                JOptionPane
                        .showMessageDialog(PlayerPanel.this, card.getLabel());
                displayInfo();
            }
        });

        btnTrade.addActionListener(new ActionListener() {
            /**
             * Handles the trade button click event and triggers the associated game action.
             * 
             * @param e the ActionEvent triggered by pressing the trade button
             */
            public void actionPerformed(ActionEvent e) {
                GameMaster.instance().btnTradeClicked();
            }
        });
    }

    /**
     * Updates the panel to display the current player's name, money, and
     * properties.
     */
    public void displayInfo() {
        lblName.setText(player.getName());
        lblMoney.setText("$ " + player.getMoney());
        StringBuffer buf = new StringBuffer();
        Cell[] cells = player.getAllProperties();
        for (int i = 0; i < cells.length; i++) {
            buf.append(cells[i] + "\n");
        }
        txtProperty.setText(buf.toString());
    }

    /**
     * Checks if the buy house button is currently enabled.
     * 
     * @return true if the buy house button is enabled, false otherwise
     */
    public boolean isBuyHouseButtonEnabled() {
        return btnBuyHouse.isEnabled();
    }

    /**
     * Checks if the draw card button is currently enabled.
     * 
     * @return true if the draw card button is enabled, false otherwise
     */
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCard.isEnabled();
    }

    /**
     * Checks if the end turn button is currently enabled.
     * 
     * @return true if the end turn button is enabled, false otherwise
     */
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurn.isEnabled();
    }

    /**
     * Checks if the get out of jail button is currently enabled.
     * 
     * @return true if the get out of jail button is enabled, false otherwise
     */
    public boolean isGetOutOfJailButtonEnabled() {
        return btnGetOutOfJail.isEnabled();
    }

    /**
     * Checks if the purchase property button is currently enabled.
     * 
     * @return true if the purchase property button is enabled, false otherwise
     */
    public boolean isPurchasePropertyButtonEnabled() {
        return btnPurchaseProperty.isEnabled();
    }

    /**
     * Checks if the roll dice button is currently enabled.
     * 
     * @return true if the roll dice button is enabled, false otherwise
     */
    public boolean isRollDiceButtonEnabled() {
        return btnRollDice.isEnabled();
    }

    /**
     * Checks if the trade button is currently enabled.
     * 
     * @return true if the trade button is enabled, false otherwise
     */
    public boolean isTradeButtonEnabled() {
        return btnTrade.isEnabled();
    }

    /**
     * Enables or disables the buy house button.
     * 
     * @param b true to enable the button, false to disable
     */
    public void setBuyHouseEnabled(boolean b) {
        btnBuyHouse.setEnabled(b);
    }

    /**
     * Enables or disables the draw card button.
     * 
     * @param b true to enable the button, false to disable
     */
    public void setDrawCardEnabled(boolean b) {
        btnDrawCard.setEnabled(b);
    }

    /**
     * Enables or disables the end turn button.
     * 
     * @param enabled true to enable the button, false to disable
     */
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurn.setEnabled(enabled);
    }

    /**
     * Enables or disables the get out of jail button.
     * 
     * @param b true to enable the button, false to disable
     */
    public void setGetOutOfJailEnabled(boolean b) {
        btnGetOutOfJail.setEnabled(b);
    }

    /**
     * Enables or disables the purchase property button.
     * 
     * @param enabled true to enable the button, false to disable
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
        btnPurchaseProperty.setEnabled(enabled);
    }

    /**
     * Enables or disables the roll dice button.
     * 
     * @param enabled true to enable the button, false to disable
     */
    public void setRollDiceEnabled(boolean enabled) {
        btnRollDice.setEnabled(enabled);
    }

    /**
     * Enables or disables the trade button.
     * 
     * @param b true to enable the button, false to disable
     */
    public void setTradeEnabled(boolean b) {
        btnTrade.setEnabled(b);
    }
}