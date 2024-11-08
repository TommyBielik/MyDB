package ASCIITable;

import Database.Row;
import Database.Table;

/**
 * This class generates and displays an ASCII table representation of database tables.
 * It includes methods for finding the maximum length of each column, building line separators,
 * formatting headers, and creating data rows.
 *
 * COLUMNS ENTERING printASCIITable METHOD MUST BE IN FORMAT: columnName, columnName, columnName....
 */

public class ASCIITable {

    // FIND MAXIMUM LENGTH OF EACH COLUMN AND STORE IT INTO AN ARRAY
    public int[] getMaxColumnLengths(String columns, Table table) {
        if(columns.equals("*")) {
            columns = table.getColumnNamesString();
        }
        String[] columnsParts = columns.split(",");
        int[] maxColumnLengths = new int[columnsParts.length];

        // FIND MAXIMUM LENGTH OF WORD
        for(int cols = 0; cols < columnsParts.length; cols++) {
            int currentMax = columnsParts[cols].length();
            for(int rows = 0; rows < table.getTableRows().size(); rows++) {
                Row row = table.getTableRows().get(rows);
                String value = String.valueOf(row.getRow().get(columnsParts[cols]));

                if(value.length() > currentMax) {
                    currentMax = value.length();
                }
            }
            maxColumnLengths[cols] = currentMax;
        }
        return maxColumnLengths;
    }

    // THIS WILL CREATE LINE SEPARATORS
    public String buildLineSeparator(String[] columnsParts, int[] maxColumnLengths) {
        String lineSeparator = "";
        for(int i = 0; i < columnsParts.length; i++) {
            lineSeparator += "+" + "-".repeat(maxColumnLengths[i]);
        }
        lineSeparator += "+\n";

        return lineSeparator;
    }

    // HEADER ROW
    public String buildHeaderRow(String[] columnsParts, int[] maxColumnLengths) {
        StringBuilder header = new StringBuilder();

        int index = 0;
        for(String key : columnsParts) {
            String stringFormat = "%-" + maxColumnLengths[index] + "s";

            header.append("|").append(String.format(stringFormat, key.trim()));
            index++;
        }
        header.append("|\n");
        return header.toString();
    }

    // DATA ROWS
    public String buildDataRows(String[] columnsParts, int[] maxColumnLengths, String inputExpression, Table table) {

        TheShuntingYard evaluation = new TheShuntingYard(inputExpression);
        StringBuilder dataRows = new StringBuilder();

        for(Row row : table.getTableRows()) {
            // Evaluate if the row meets the condition specified by inputExpression
            if(evaluation.evaluateWhereClause(row)) {
                int index = 0;
                for (String value : columnsParts) {
                    String stringFormat = "%-" + maxColumnLengths[index] + "s";
                    String numberFormat = "%" + maxColumnLengths[index] + "d";
                    if (row.getData(value.trim()).getClass().getSimpleName().equals("String") ||
                            row.getData(value).getClass().getSimpleName().equals("Boolean")) {
                        dataRows.append("|").append(String.format(stringFormat, row.getData(value)));
                    } else {
                        dataRows.append("|").append(String.format(numberFormat, row.getData(value)));
                    }
                    index++;
                }
                dataRows.append("|\n");
            }
        }
        return dataRows.toString();
    }

    public void printASCIITable(Table table, String columns, String inputExpression) {
        if(columns.equals("*")) {
            columns = table.getColumnNamesString();
        }
        String[] columnsParts = columns.split(",");

        int[] maxColumnLengths = getMaxColumnLengths(columns, table);

        // START OF ASCIITABLE BUILD
        String lineSeparator = buildLineSeparator(columnsParts, maxColumnLengths);
        String header = buildHeaderRow(columnsParts, maxColumnLengths);
        String dataRows = buildDataRows(columnsParts, maxColumnLengths, inputExpression, table);

        String asciiTable = lineSeparator +
                header +
                lineSeparator +
                dataRows +
                lineSeparator;

        System.out.println(asciiTable);
    }
}
