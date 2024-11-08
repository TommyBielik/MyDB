package UserInterface;

import java.util.Scanner;

import ASCIITable.ASCIITable;
import Database.Database;
import operations.*;
import program.*;
import static serializer.Serializer.*;

public class UserInterface {

    private final Program program;
    private final ASCIITable asciiTable;

    private final CreateDatabase createDatabase;
    private final LoadDatabase loadDatabase;
    private final DeleteDatabase deleteDatabase;

    private final CreateTableOperation createTableOperation;
    private final InsertIntoOperation insertIntoOperation;
    private final SelectOperation selectOperation;
    private final DeleteOperation deleteOperation;
    private final DropTableOperation dropTableOperation;
    private final UpdateTableOperation updateTableOperation;

    boolean inDatabase = false;
    boolean isRunning = true;

    private Database database;

    public UserInterface(Program program) {
        this.program = program;

        this.asciiTable = new ASCIITable();
        this.createDatabase = new CreateDatabase();
        this.loadDatabase = new LoadDatabase();
        this.deleteDatabase = new DeleteDatabase();

        this.createTableOperation = new CreateTableOperation();
        this.insertIntoOperation = new InsertIntoOperation();
        this.selectOperation = new SelectOperation();
        this.deleteOperation = new DeleteOperation();
        this.dropTableOperation = new DropTableOperation();
        this.updateTableOperation = new UpdateTableOperation();
    }

    public void start() {

        while(isRunning) {
            if(!inDatabase) {
                selectOrCreateDatabase();
            } else {
                manageDatabase();
            }
        }

        // Save and quit
        serialize(program);
        System.out.println("Quitting MyDB...");
    }

    public void selectOrCreateDatabase() {

        System.out.println("-------------COMMANDS:------------");
        System.out.println("---------CREATE DATABASE----------");
        System.out.println("----------LOAD DATABASE-----------");
        System.out.println("---------DELETE DATABASE-----------");
        System.out.println("--------------QUIT----------------");

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.print("Command: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if(input.equals("create database")) {
                database = createDatabase.execute(scanner, program);
                inDatabase = true;
                return;
            }

            if(input.equals("load database")) {
                if(program.getProgram().isEmpty()) {
                    System.out.println("There is no database in the program. Do you want to add database? Y/N");
                    String answer = scanner.nextLine().toLowerCase().trim();
                    if(answer.equals("y")) {
                        database = createDatabase.execute(scanner, program);
                    }

                    if(answer.equals("n")) {
                        continue;
                    }

                    else{
                        System.out.println("Invalid command.");
                        continue;
                    }
                }
                database = loadDatabase.execute(scanner, program);
                inDatabase = true;
                return;
            }

            if(input.equals("delete database")) {
                deleteDatabase.execute(scanner, program);
                continue;
            }

            if(input.equals("quit")) {
                isRunning = false;
                return;
            }
        }
    }

    public void manageDatabase() {

        System.out.println("-----------THIS IS MYDB-----------");
        System.out.println("---Enter 'quit' to exit program---");
        System.out.println("-------------COMMANDS:------------");
        System.out.println("-----------CREATE TABLE-----------");
        System.out.println("-----------INSERT INTO------------");
        System.out.println("-------------SELECT---------------");
        System.out.println("-------------UPDATE---------------");
        System.out.println("-------------DELETE---------------");
        System.out.println("-----------DROP TABLE-------------");
        System.out.println("--------------SAVE----------------");
        System.out.println("------------.schema---------------");
        System.out.println("--------------MENU----------------");

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.print("Command: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("create table")) {
                createTableOperation.execute(database, asciiTable, scanner);
                continue;
            }

            if (input.equals("quit")) {
                isRunning = false;
                scanner.close();
                break;
            }

            if (input.equals(".schema")) {
                System.out.print(database);
            }

            if (input.equals("select")) {
                selectOperation.execute(database, asciiTable, scanner);
                continue;
            }

            if (input.equals("insert into")) {
                insertIntoOperation.execute(database, asciiTable, scanner);
                continue;
            }

            if (input.equals("delete")) {
                deleteOperation.execute(database, asciiTable, scanner);
                continue;
            }

            if (input.equals("save")) {
                serialize(program);
                continue;
            }

            if (input.equals("menu")) {
                inDatabase = false;
                break;
            }

            if (input.equals("drop table")) {
                dropTableOperation.execute(database, asciiTable, scanner);
                continue;
            }

            if (input.equals("update")) {
                updateTableOperation.execute(database, asciiTable, scanner);
            }
        }
    }
}
