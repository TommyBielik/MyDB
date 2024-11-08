package operations;

import ASCIITable.*;
import Database.Database;
import Database.Table;
import Database.Row;
import static helpers.ConvertValue.convertValue;

import java.util.Scanner;

public class UpdateTableOperation implements Operation {
    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {

        String tableName = "";

        while(tableName.isEmpty()) {
            System.out.print("Table: ");
            tableName = scanner.nextLine().trim();
            if(!database.checkTableExists(tableName)) {
                System.out.println("Table " + tableName + " does not exist.");
                continue;
            }
            break;
        }

        Table table = database.getTable(tableName);

        System.out.print("SET Columns: ");
        String columns = scanner.nextLine().trim();
        String columnNames = getColumnNames(columns);

        System.out.print("Where: ");
        String where = scanner.nextLine().trim();

        // Update and print table
        updateTable(table, columns, where);
        asciiTable.printASCIITable(table, columnNames, where);
    }

    public void updateTable(Table table, String columns, String where) {

        // Separate each value user wants to set
        String[] columnsParts = columns.split(",");
        TheShuntingYard shuntingYard = new TheShuntingYard(where);

        for(Row row : table.getTableRows()) {
            if(shuntingYard.evaluateWhereClause(row)) {
                setColumns(columnsParts, row);
            }
        }
    }

    public void setColumns(String[] columnsParts, Row row) {
        for(int i = 0; i < columnsParts.length; i++) {
            String currentColumn = columnsParts[i];
            String[] currentColumnParts = currentColumn.split("=");

            String key = currentColumnParts[0].trim();
            Object value = convertValue(currentColumnParts[1].trim());

            row.updateData(key, value);
        }
    }

    public String getColumnNames(String columns) {
        String[] columnsParts = columns.split(",");
        String columnNames = "";
        for(int i = 0; i < columnsParts.length; i++) {
            String[] columnNameAndValue = columnsParts[i].split("=");
            String key = columnNameAndValue[0].trim();
            columnNames += key + ",";
        }
        return columnNames;
    }
}
