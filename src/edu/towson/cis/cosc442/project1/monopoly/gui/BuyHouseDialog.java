
package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.towson.cis.cosc442.project1.monopoly.Player;

public class BuyHouseDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<?> cboMonopoly;
	private JComboBox<?> cboNumber;

	private Player player;

	/**
	 * Constructs a BuyHouseDialog for the given player, initializing the GUI
	 * components for selecting a monopoly and number of houses to buy.
	 * 
	 * @param player the Player instance who will purchase houses
	 */
	public BuyHouseDialog(Player player) {
		this.player = player;
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(3, 2));
		c.add(new JLabel("Select monopoly"));
		c.add(buildMonopolyComboBox());
		c.add(new JLabel("Number of houses"));
		c.add(buildNumberComboBox());
		c.add(buildOKButton());
		c.add(buildCancelButton());
		c.doLayout();
		this.pack();
	}

	/**
	 * Creates and returns a JButton labeled 'Cancel' that disposes the dialog when
	 * clicked.
	 * 
	 * @return a JButton configured as a cancel button
	 */
	private JButton buildCancelButton() {
		JButton btn = new JButton("Cancel");
		btn.addActionListener(new ActionListener() {
			/**
			 * Handles action events triggered by the OK button by invoking okClicked.
			 * 
			 * @param e the event triggered by clicking the OK button
			 */
			public void actionPerformed(ActionEvent e) {
				cancelClicked();
			}
		});
		return btn;
	}

	/**
	 * Builds and returns a JComboBox populated with the player's monopolies.
	 * 
	 * @return a JComboBox listing the player's monopolies
	 */
	private JComboBox<?> buildMonopolyComboBox() {
		cboMonopoly = new JComboBox<Object>(player.getMonopolies());
		return cboMonopoly;
	}

	/**
	 * Builds and returns a JComboBox allowing selection of the number of houses to
	 * purchase, from 1 to 5.
	 * 
	 * @return a JComboBox for selecting the number of houses
	 */
	private JComboBox<?> buildNumberComboBox() {
		cboNumber = new JComboBox<Object>(new Integer[] {
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5) });
		return cboNumber;
	}

	/**
	 * Creates and returns a JButton labeled 'OK' that triggers house purchase and
	 * closes the dialog when clicked.
	 * 
	 * @return a JButton configured as an OK button
	 */
	private JButton buildOKButton() {
		JButton btn = new JButton("OK");
		btn.addActionListener(new ActionListener() {
			/**
			 * Handles action events triggered by the OK button by invoking okClicked.
			 * 
			 * @param e the event triggered by clicking the OK button
			 */
			public void actionPerformed(ActionEvent e) {
				okClicked();
			}
		});
		return btn;
	}

	/**
	 * Disposes of the dialog, effectively cancelling the house purchase process.
	 */
	private void cancelClicked() {
		this.dispose();
	}

	/**
	 * Retrieves selected monopoly and house number, instructs the player to
	 * purchase houses accordingly, then closes the dialog.
	 */
	private void okClicked() {
		String monopoly = (String) cboMonopoly.getSelectedItem();
		int number = cboNumber.getSelectedIndex() + 1;
		player.purchaseHouse(monopoly, number);
		this.dispose();
	}
}
