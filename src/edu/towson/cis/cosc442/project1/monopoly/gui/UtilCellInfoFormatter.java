package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;
import edu.towson.cis.cosc442.project1.monopoly.Player;
import edu.towson.cis.cosc442.project1.monopoly.UtilityCell;

public class UtilCellInfoFormatter implements CellInfoFormatter {

        /**
         * Formats the given Cell object as an HTML string displaying its name, price,
         * and owner information.
         * 
         * @param cell the Cell object to format
         * @return an HTML-formatted string representing the utility cell's details
         *         including name, price, and owner
         */
        public String format(Cell cell) {
                UtilityCell c = (UtilityCell) cell;
                StringBuffer buf = new StringBuffer();
                Player owner = cell.getTheOwner();
                String ownerName = "";
                if (owner != null) {
                        ownerName = owner.getName();
                }
                buf.append("<html><b><font color='olive'>")
                                .append(cell.getName())
                                .append("</font></b><br>")
                                .append("$").append(c.getPrice())
                                .append("<br>Owner: ").append(ownerName)
                                .append("</html>");
                return buf.toString();
        }
}
