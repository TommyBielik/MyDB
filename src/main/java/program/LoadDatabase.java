package program;

import Database.Database;

import java.util.Scanner;

/* This operation loads selected database from program
* */
public class LoadDatabase {
    public Database execute(Scanner scanner, Program program) {

        String databaseName;
        System.out.println("Databases: " + program.toString());

        while(true) {
            System.out.print("Select database: ");
            databaseName = scanner.nextLine();

            if(!program.isInProgram(databaseName)) {
                System.out.println("Database does not exist.");
                continue;
            }

            Database database = program.getDatabase(databaseName);
            System.out.println("Database " + databaseName + " loaded.");
            return database;
        }
    }
}
