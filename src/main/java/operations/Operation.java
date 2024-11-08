package operations;

import ASCIITable.ASCIITable;
import Database.Database;

import java.util.Scanner;

public interface Operation {

    public void execute(Database database, ASCIITable asciiTable, Scanner scanner);
}
