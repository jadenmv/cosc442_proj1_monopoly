package edu.towson.cis.cosc442.project1.monopoly.gui;

import java.util.Hashtable;

import edu.towson.cis.cosc442.project1.monopoly.*;

public class InfoFormatter {
        static Hashtable<Class<?>, CellInfoFormatter> cellInfoFormatters = null;

        static {
                if (cellInfoFormatters == null) {
                        cellInfoFormatters = new Hashtable<Class<?>, CellInfoFormatter>();
                        addFormatters();
                }
        }

        /**
         * Registers default formatters for each subclass of Cell in the
         * cellInfoFormatters map.
         */
        private static void addFormatters() {
                cellInfoFormatters.put(
                                PropertyCell.class, new PropertyCellInfoFormatter());
                cellInfoFormatters.put(
                                GoCell.class, new GoCellInfoFormatter());
                cellInfoFormatters.put(
                                JailCell.class, new JailCellInfoFormatter());
                cellInfoFormatters.put(
                                GoToJailCell.class, new GotoJailCellInfoFormatter());
                cellInfoFormatters.put(
                                FreeParkingCell.class, new FreeParkingCellInfoFormatter());
                cellInfoFormatters.put(
                                RailRoadCell.class, new RRCellInfoFormatter());
                cellInfoFormatters.put(
                                UtilityCell.class, new UtilCellInfoFormatter());
                cellInfoFormatters.put(
                                CardCell.class, new CCCellInfoFormatter());
        }

        /**
         * Returns a formatted string describing the given Cell using its corresponding
         * formatter.
         * 
         * @param cell the Cell instance to format
         * @return a formatted information string for the specified cell
         */
        public static String cellInfo(Cell cell) {
                CellInfoFormatter formatter = (CellInfoFormatter) cellInfoFormatters.get(cell.getClass());
                return formatter.format(cell);
        }

}
