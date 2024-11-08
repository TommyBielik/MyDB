package Database;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {

    private final ArrayList<Table> database;
    private final String name;
    private static final long serialVersionUID = 1L;

    public Database(String name) {
        this.database = new ArrayList();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addTable(Table table) {
        this.database.add(table);
    }

    public void removeTable(Table table) {
        this.database.remove(table);
    }

    public Table getTable(String name) {
        for(Table table : database) {
            if(table.getName().equals(name)) {
                return table;
            }
        }
        return null;
    }

    public boolean checkTableExists(String name) {
        for(Table table : database) {
            if(table.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "";
        for(Table table : this.database) {
            string += table.toString() + "\n";
        }
        return string;
    }
}
