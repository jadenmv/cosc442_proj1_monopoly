package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;
import edu.towson.cis.cosc442.project1.monopoly.Player;
import edu.towson.cis.cosc442.project1.monopoly.PropertyCell;

public class PropertyCellInfoFormatter implements CellInfoFormatter {
    /**
     * Formats the information of a given property cell into an HTML string
     * displaying its name, price, owner, and number of houses.
     * 
     * @param cell the property cell to format
     * @return a formatted HTML string representing the property cell's details
     */
    public String format(Cell cell) {
        PropertyCell c = (PropertyCell) cell;
        StringBuffer buf = new StringBuffer();
        Player owner = cell.getTheOwner();
        String ownerName = "";
        if (owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='")
                .append(c.getColorGroup())
                .append("'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
                .append("<br>Owner: ").append(ownerName)
                .append("<br>* ").append(c.getNumHouses())
                .append("</html>");
        return buf.toString();
    }
}
