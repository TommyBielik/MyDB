package operations;

import ASCIITable.ASCIITable;
import Database.Database;
import Database.Table;

import java.util.Scanner;

/* Selects rows from table based on inputted where clause.
 * Then it prints asciitable of those rows.
 * */
public class SelectOperation implements Operation {

    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {
        String columns = "";
        String tableName;
        String where;

        while(columns.isEmpty()) {
            System.out.print("Columns: ");
            columns = scanner.nextLine().replaceAll("\\s", "");
        }

        while(true) {
            System.out.print("From: ");
            tableName = scanner.nextLine().trim();

            // CHECK IF TABLE EXISTS IN DATABASE
            if(!database.checkTableExists(tableName)) {
                System.out.println("Table " + tableName + " does not exist");
                continue;
            }

            Table table = database.getTable(tableName);

            // CHECK IF INPUTTED COLUMNS NAMES EXIST
            if(!table.columnsExist(columns)) {
                System.out.println("Columns " + table.getNonExistentColumn(columns) + " do not exist.");
                return;
            }
            else {
                break;
            }
        }

        System.out.print("Where: ");
        where = scanner.nextLine().trim();

        Table table = database.getTable(tableName);

        asciiTable.printASCIITable(table, columns, where);
    }
}
