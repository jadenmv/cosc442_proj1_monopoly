package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class GUITradeDialog extends JDialog implements TradeDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JButton btnOK, btnCancel;
    private JComboBox<Object> cboSellers, cboProperties;

    private TradeDeal deal;
    private JTextField txtAmount;

    /**
     * Constructs a modal dialog for property trading with the specified parent
     * frame.
     * 
     * @param parent the parent frame of this dialog
     */
    public GUITradeDialog(Frame parent) {
        super(parent);

        setTitle("Trade Property");
        cboSellers = new JComboBox<Object>();
        cboProperties = new JComboBox<Object>();
        txtAmount = new JTextField();
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");

        btnOK.setEnabled(false);

        buildSellersCombo();
        setModal(true);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Sellers"));
        contentPane.add(cboSellers);
        contentPane.add(new JLabel("Properties"));
        contentPane.add(cboProperties);
        contentPane.add(new JLabel("Amount"));
        contentPane.add(txtAmount);
        contentPane.add(btnOK);
        contentPane.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            /**
             * Handles the Cancel button action to hide the trade dialog.
             * 
             * @param e the action event triggered by the Cancel button
             */
            public void actionPerformed(ActionEvent e) {
                GUITradeDialog.this.hide();
            }
        });

        cboSellers.addItemListener(new ItemListener() {
            /**
             * Updates the properties combo box when the selected seller changes.
             * 
             * @param e the item event indicating the seller selection change
             */
            public void itemStateChanged(ItemEvent e) {
                Player player = (Player) e.getItem();
                updatePropertiesCombo(player);
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            /**
             * Handles the Cancel button action to hide the trade dialog.
             * 
             * @param e the action event triggered by the Cancel button
             */
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                try {
                    amount = Integer.parseInt(txtAmount.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(GUITradeDialog.this,
                            "Amount should be an integer", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Cell cell = (Cell) cboProperties.getSelectedItem();
                if (cell == null)
                    return;
                Player player = (Player) cboSellers.getSelectedItem();
                Player currentPlayer = GameMaster.instance().getCurrentPlayer();
                if (currentPlayer.getMoney() > amount) {
                    deal = new TradeDeal();
                    deal.setAmount(amount);
                    deal.setPropertyName(cell.getName());
                    deal.setSellerIndex(GameMaster.instance().getPlayerIndex(player));
                }
                hide();
            }
        });

        this.pack();
    }

    /**
     * Populates the sellers combo box with available sellers and updates properties
     * for the first seller.
     */
    private void buildSellersCombo() {
        List<?> sellers = GameMaster.instance().getSellerList();
        for (Iterator<?> iter = sellers.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            cboSellers.addItem(player);
        }
        if (sellers.size() > 0) {
            updatePropertiesCombo((Player) sellers.get(0));
        }
    }

    /**
     * Returns the trade deal created from user input in the dialog.
     * 
     * @return the TradeDeal object representing the trade or null if none was
     *         created
     */
    public TradeDeal getTradeDeal() {
        return deal;
    }

    /**
     * Updates the properties combo box with properties owned by the specified
     * player and adjusts the OK button enabled state.
     * 
     * @param player the player whose properties are to be displayed
     */
    private void updatePropertiesCombo(Player player) {
        cboProperties.removeAllItems();
        Cell[] cells = player.getAllProperties();
        btnOK.setEnabled(cells.length > 0);
        for (int i = 0; i < cells.length; i++) {
            cboProperties.addItem(cells[i]);
        }
    }

}
