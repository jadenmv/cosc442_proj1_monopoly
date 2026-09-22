package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;
import edu.towson.cis.cosc442.project1.monopoly.Player;
import edu.towson.cis.cosc442.project1.monopoly.RailRoadCell;

public class RRCellInfoFormatter implements CellInfoFormatter {
    /**
     * Formats the given Cell object as an HTML string displaying its name, price,
     * and owner information.
     * 
     * @param cell the Cell object to be formatted
     * @return an HTML string representation of the cell's details including name,
     *         price, and owner
     */
    public String format(Cell cell) {
        RailRoadCell c = (RailRoadCell) cell;
        StringBuffer buf = new StringBuffer();
        Player owner = cell.getTheOwner();
        String ownerName = "";
        if (owner != null) {
            ownerName = owner.getName();
        }
        buf.append("<html><b><font color='lime'>")
                .append(cell.getName())
                .append("</font></b><br>")
                .append("$").append(c.getPrice())
                .append("<br>Owner: ").append(ownerName)
                .append("</html>");
        return buf.toString();
    }
}
