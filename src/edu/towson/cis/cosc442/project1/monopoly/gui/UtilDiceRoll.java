package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.towson.cis.cosc442.project1.monopoly.GameMaster;

public class UtilDiceRoll extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	/**
	 * Displays a modal dialog that prompts the user to roll dice and returns the
	 * sum of the dice roll.
	 * 
	 * @return The sum of the dice values rolled by the user.
	 */
	public static int showDialog() {
		UtilDiceRoll dialog = new UtilDiceRoll();
		dialog.show();
		return dialog.diceValue;
	}

	JButton btnDice = new JButton("Roll the Dice!");
	private JButton btnOK = new JButton("OK");
	private int diceValue;
	private JLabel lblPrompt = new JLabel();

	/**
	 * Constructs a modal dialog with controls to roll dice and confirm the result
	 * for utility billing.
	 */
	public UtilDiceRoll() {
		setModal(true);
		btnOK.setEnabled(false);
		lblPrompt.setText("Please roll the dice to determine your utility bill.");
		Container contentPane = getContentPane();
		JPanel pnlButtons = new JPanel();
		pnlButtons.add(btnDice);
		pnlButtons.add(btnOK);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(lblPrompt, BorderLayout.CENTER);
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		btnDice.addActionListener(new ActionListener() {
			/**
			 * Handles the action event triggered by the OK button to close the dialog.
			 * 
			 * @param arg0 The action event triggered by pressing the OK button.
			 */
			public void actionPerformed(ActionEvent arg0) {
				rollDice();
			}
		});
		btnOK.addActionListener(new ActionListener() {
			/**
			 * Handles the action event triggered by the OK button to close the dialog.
			 * 
			 * @param arg0 The action event triggered by pressing the OK button.
			 */
			public void actionPerformed(ActionEvent arg0) {
				okClicked();
			}
		});
		this.pack();
	}

	/**
	 * Closes the dialog when the OK button is clicked.
	 */
	public void okClicked() {
		this.dispose();
	}

	/**
	 * Rolls two dice using the game master and updates the UI with the result,
	 * enabling the OK button.
	 */
	public void rollDice() {
		int[] diceRoll = GameMaster.instance().rollDice();
		this.diceValue = diceRoll[0] + diceRoll[1];
		lblPrompt.setText("You rolled " + diceValue);
		btnDice.setEnabled(false);
		btnOK.setEnabled(true);
	}
}
