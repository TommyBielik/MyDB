package operations;

import ASCIITable.ASCIITable;
import Database.Database;

import java.util.Scanner;

/* Deletes whole table from current database
* */
public class DropTableOperation implements Operation {
    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {

        System.out.print("Table name: ");
        String tableName = scanner.nextLine();
        if(!database.checkTableExists(tableName)) {
            System.out.println("Table does not exist.");
            return;
        }

        database.removeTable(database.getTable(tableName));
        System.out.println("Table " + tableName + " removed successfully.");
    }
}
