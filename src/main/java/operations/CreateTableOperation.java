package operations;

import ASCIITable.ASCIITable;
import Database.Database;
import Database.Table;

import java.util.Scanner;


public class CreateTableOperation implements Operation {

    @Override
    public void execute(Database database, ASCIITable asciiTable, Scanner scanner) {
        String tableName = "";

        while(true) {
            System.out.print("Name: ");
            tableName = scanner.nextLine().trim();

            if(database.checkTableExists(tableName)) {
                System.out.println("Table already exists!");
                continue;
            }

            if(!tableName.isEmpty()) {
                break;
            }
        }

        Table table = new Table(tableName);

        insertColumns(scanner, table);
        database.addTable(table);
    }

    public void insertColumns(Scanner scanner, Table table) {
        String[] allowedTypes = {"String", "Boolean", "Integer", "Double"};

        while(true) {
            System.out.print("Insert column: ");
            String column = scanner.nextLine();

            // HANDLE SITUATION WHERE USER HAVENT ENTERED A COLUMN YET
            if(column.isEmpty() && !table.getColumnTypes().isEmpty()) {
                break;
            }

            String[] parts = column.split(" ");
            if(parts.length != 2) {
                System.out.println("Usage: key value");
                continue;
            }

            if(!isAllowedType(allowedTypes, parts[1])) {
                System.out.println("Not allowed type: " + parts[1]);
                continue;
            }


            table.addColumnTypes(parts[0], parts[1]);
        }
    }

    public boolean isAllowedType(String[] allowedTypes, String type) {
        boolean isAllowedType = false;
        for(String allowedType : allowedTypes) {
            if(type.equals(allowedType)) {
                isAllowedType =  true;
            }
        }
        return isAllowedType;
    }

}
