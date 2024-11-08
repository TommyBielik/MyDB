package operations;

import ASCIITable.ASCIITable;
import ASCIITable.TheShuntingYard;
import Database.Database;
import Database.Table;
import Database.Row;
import java.util.Iterator;
import java.util.Scanner;

/* Prompts the user for table from which wants to remove rows,
*  then removes the rows based on where clause evaluation
*  */
public class DeleteOperation implements Operation {
    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {
        String tableName;
        String where;

        while(true) {
            System.out.print("From: ");
            tableName = scanner.nextLine().toLowerCase().trim();

            // Checks if table exists in the database
            if(!database.checkTableExists(tableName)) {
                System.out.println("Table " + tableName + " does not exist");
                continue;
            }

            System.out.print("Where: ");
            where = scanner.nextLine().toLowerCase().trim();

            // Removes all table rows
            if(where.isEmpty()) {
                database.getTable(tableName).getTableRows().clear();
                break;
            }

            Table table = database.getTable(tableName);
            TheShuntingYard evaluation = new TheShuntingYard(where);

            deleteSelectedRows(evaluation, table);
            asciiTable.printASCIITable(table, "*", "");
            break;
        }
    }

    // Removes table rows that meet the condition of given where clause
    public void deleteSelectedRows(TheShuntingYard evaluation, Table table) {
        Iterator<Row> iterator = table.getTableRows().iterator();

        while(iterator.hasNext()) {
            Row row = iterator.next();

            if(evaluation.evaluateWhereClause(row)) {
                iterator.remove();
            }
        }
    }
}
