package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class GUICell extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cell cell;
	private JLabel lblInfo;
	private JLabel[] lblPlayers = new JLabel[GameMaster.MAX_PLAYER];

	/**
	 * Constructs a GUICell panel representing the specified Cell with initial
	 * layout and player labels.
	 * 
	 * @param cell the Cell instance that this GUICell represents
	 */
	public GUICell(Cell cell) {
		this.cell = cell;
		setLayout(new OverlayLayout(this));
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel pnlPlayer = new JPanel();
		pnlPlayer.setLayout(new GridLayout(2, 4));
		pnlPlayer.setOpaque(false);
		createPlayerLabels(pnlPlayer);
		add(pnlPlayer);
		setPreferredSize(new Dimension(100, 100));
		addCellInfo();
		this.doLayout();
	}

	/**
	 * Adds and initializes the cell information display label to the panel.
	 */
	private void addCellInfo() {
		lblInfo = new JLabel();
		displayInfo();
		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new GridLayout(1, 1));
		pnlInfo.add(lblInfo);
		add(pnlInfo);
	}

	/**
	 * Displays the player's initial on the GUI cell at the given player index and
	 * highlights it.
	 * 
	 * @param index the index of the player to add to the cell display
	 */
	public void addPlayer(int index) {
		Player player = GameMaster.instance().getPlayer(index);
		lblPlayers[index].setText(player.getName().substring(0, 1));
		lblPlayers[index].setOpaque(true);
	}

	/**
	 * Initializes and adds player label components to the given player panel for
	 * all possible players.
	 * 
	 * @param pnlPlayer the JPanel container to add player labels into
	 */
	private void createPlayerLabels(JPanel pnlPlayer) {
		for (int i = 0; i < GameMaster.MAX_PLAYER; i++) {
			lblPlayers[i] = new JLabel();
			lblPlayers[i].setBackground(Color.GREEN);
			pnlPlayer.add(lblPlayers[i]);
		}
	}

	/**
	 * Updates the cell information label text and repaints the GUI cell to reflect
	 * current cell info.
	 */
	public void displayInfo() {
		lblInfo.setText(InfoFormatter.cellInfo(cell));
		this.invalidate();
		this.repaint();
	}

	/**
	 * Returns the Cell object associated with this GUICell.
	 * 
	 * @return the Cell represented by this GUICell
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Removes the player's display from the cell at the given player index and
	 * refreshes the GUI.
	 * 
	 * @param index the index of the player to remove from the cell display
	 */
	public void removePlayer(int index) {
		lblPlayers[index].setText("");
		lblPlayers[index].setOpaque(false);
		this.repaint();
	}
}
