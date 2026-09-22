package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.Cell;

public class CCCellInfoFormatter implements CellInfoFormatter {
    /**
     * Formats the provided Cell object's name as an HTML string with white bold
     * font for display purposes.
     * 
     * @param cell the Cell object to be formatted
     * @return an HTML-formatted string representing the cell's name
     */
    public String format(Cell cell) {
        return "<html><font color='white'><b>" + cell.getName() + "</b></font></html>";
    }
}
