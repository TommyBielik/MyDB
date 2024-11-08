package Database;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Represents a single row in a database table.
 * Each row consists of multiple columns, stored as key-value pairs
 * where the key is the column name and the value is the data.
 */

public class Row  implements Serializable {

    // Stores the columns of the row with their associated data.
    private final LinkedHashMap<String, Object> row;
    private static final long serialVersionUID = 1L;

    public Row() {
        this.row = new LinkedHashMap<>();
    }

    public LinkedHashMap getRow() {
        return this.row;
    }

    public void addColumn(String dataName, Object data) {
        this.row.put(dataName, data);
    }

    public Object getData(String dataName) {
        return this.row.get(dataName);
    }

    public void updateData(String dataName, Object data) {
        if(this.row.containsKey(dataName)) {
            this.row.put(dataName, data);
        } else {
            System.out.println("Column " + dataName + " not found.");
        }
    }

    @Override
    public String toString() {
        String values = "";
        for(String key : this.row.keySet()) {
            values += this.row.get(key) + ",";
        }
        return values;
    }
}
