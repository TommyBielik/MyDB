package program;

import Database.Database;

import java.util.Iterator;
import java.util.Scanner;

public class DeleteDatabase {

    public void execute(Scanner scanner, Program program) {

        System.out.print("Database name: ");
        String databaseName = scanner.nextLine();

        Iterator<Database> iterator = program.getProgram().iterator();
        boolean found = false;

        while(iterator.hasNext()) {
            Database db = iterator.next();

            if(db.getName().equals(databaseName)) {
                iterator.remove();
                found = true;
            }
        }

        if(!found) {
            System.out.println("Database " + databaseName + " not found");
        } else {
            System.out.println("Database " + databaseName + " removed successfully.");
        }
    }
}
