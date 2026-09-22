package edu.towson.cis.cosc442.project1.monopoly;

public class CardCell extends Cell {
    private int type;

    /**
     * Constructs a CardCell with the specified type and name.
     * 
     * @param type the integer type of the CardCell
     * @param name the name of the CardCell
     */
    public CardCell(int type, String name) {
        setName(name);
        this.type = type;
    }

    /**
     * Executes the action associated with this CardCell, currently not implemented.
     */
    public void playAction() {
    }

    /**
     * Returns the type of this CardCell.
     * 
     * @return the integer type of this CardCell
     */
    public int getType() {
        return type;
    }
}
