package operations;

import ASCIITable.ASCIITable;
import Database.Database;
import Database.Row;
import Database.Table;
import java.util.*;
import static helpers.ConvertValue.convertValue;

/* Prompts the user with table name, if provided,
 * prompts again for values user wants to input into the table
 * */
public class InsertIntoOperation implements Operation {

    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {
        String tableName;

        // Get table name from user
        while(true) {
            System.out.print("Table: ");
            tableName = scanner.nextLine();

            if(!database.checkTableExists(tableName)) {
                System.out.println("Table " + tableName + " does not exist");
                continue;
            }

            break;
        }

        // Get inputted table
        Table table = database.getTable(tableName);
        LinkedHashMap<String, String> columnTypes = table.getColumnTypes();

        Row row = new Row();

        // Iterate over columns to enter values
        for(Map.Entry<String,String> entry : columnTypes.entrySet()) {
            String key = entry.getKey();
            Object value;
            while(true) {
                System.out.print(entry.getKey() + ": ");

                // Convert user input into columns corresponding data type
                value = convertValue(scanner.nextLine());

                // If inputted value is not of type of column, user will get error message
                if (!value.getClass().getSimpleName().equals(entry.getValue())) {
                    System.out.println("Value does not match with column type: " + entry.getValue() + " : " + value.getClass().getSimpleName());
                    continue;
                }
                break;
            }
            row.addColumn(key, value);
        }
        table.addRow(row);

        asciiTable.printASCIITable(table, "*", "");
    }
}
