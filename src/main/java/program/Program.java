package program;

import Database.Database;

import java.io.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Program implements Serializable {

    private final ArrayList<Database> program;
    @Serial
    private static final long serialVersionUID = 1L;

    public Program() {
        this.program = new ArrayList();
    }

    public void addDatabase(Database database) {
        this.program.add(database);
    }

    public Database getDatabase(String databaseName) {
        for(Database database : this.program) {
            if(database.getName().equals(databaseName)) {
                return database;
            }
        }
        return null;
    }

    public ArrayList<Database> getProgram() {
        return program;
    }

    public boolean isInProgram(String databaseName) {
        if(this.program.isEmpty()) {
            return false;
        }

        boolean isInProgram = false;
        for(Database db : this.program) {
            if(db.getName().equals(databaseName)) {
                isInProgram = true;
            }
        }
        return isInProgram;
    }

    @Override
    public String toString() {
        String dbs = "";
        for(Database db : this.program) {
            dbs += db.getName() + " ";
        }
        return dbs;
    }
}
