package program;

import Database.Database;

import java.util.Scanner;

/* Creates database
* */
public class CreateDatabase {

    public Database execute(Scanner scanner, Program program) {
        String databaseName;

        while(true) {
            System.out.print("Name of database: ");
            databaseName = scanner.nextLine().trim();
            if(databaseName.isEmpty()) {
                continue;
            }

            if(program.isInProgram(databaseName)) {
                System.out.println("Database " + databaseName + " already exists.");
                continue;
            }

            Database database = new Database(databaseName);

            program.addDatabase(database);
            System.out.println("Database " + databaseName + " created.");
            return database;
        }
    }
}
