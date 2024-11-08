package Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a table within a database, containing
 * a name, a list of rows, and column metadata defining the column names and data types.
 */

public class Table implements Serializable {

    private final String name;
    private final LinkedHashMap<String, String> columnTypes;
    private final ArrayList<Row> table;
    private static final long serialVersionUID = 1L;

    public Table(String name) {
        this.name = name;
        this.table = new ArrayList<>();
        this.columnTypes = new LinkedHashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Row> getTableRows() {
        return this.table;
    }

    public void addRow(Row row) {
        this.table.add(row);
    }

    public void addColumnTypes(String columnName, String columnType) {
        this.columnTypes.put(columnName, columnType);
    }

    public LinkedHashMap<String, String> getColumnTypes() {
        return this.columnTypes;
    }

    public boolean columnsExist(String columns) {
        if(columns.equals("*")) {
            return true;
        }

        String[] parts = columns.split(",");
        for(String column : parts) {
            if(!this.columnTypes.containsKey(column)) {
                return false;
            }
        }
        return true;
    }

    public String getNonExistentColumn(String columns) {
        String[] parts = columns.split(",");
        String nonExistentColumns = "";

        int count = 0;
        for(String part : parts) {
            if(!this.columnTypes.containsKey(part)) {
                count++;
                if(count > 1) {
                    nonExistentColumns += ",";
                }
                nonExistentColumns += part;
            }
        }
        return nonExistentColumns;
    }

    public String getColumnNamesString() {
        String columns = "";
        for(String column : this.columnTypes.keySet()) {
            columns += column + ",";
        }
        return columns;
    }

    @Override
    public String toString() {
        String string = "";
        LinkedHashMap<String,String> row = this.columnTypes;

        if(row.isEmpty()) {
            return string;
        }

        for(Map.Entry<String,String> entry : row.entrySet()) {
            string += entry.getKey() + " " + entry.getValue() + ";\n";
        }
        return "Table " + this.name + ":\n" + string;
    }
}
