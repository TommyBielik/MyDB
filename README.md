# MyDB

MyDB is a lightweight SQL-like database system that allows users to perform basic CRUD (Create, Read, Update, Delete) operations. This project simulates a simple database management system where you can create and manage databases, tables, and records using familiar SQL commands.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Commands](#commands)
  - [Main Menu Commands](#main-menu-commands)
  - [Database Commands](#database-commands)
  - [Table Commands](#table-commands)
  - [Query Commands](#query-commands)
- [Examples](#examples)
- [Saving and Exiting](#saving-and-exiting)

## Features
- **Case-Insensitive Commands**: MyDB accepts commands in any case (e.g., `create database` or `CReaTe DaTabase`).
- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on tables.
- **SQL-like Queries**: Use familiar SQL syntax such as `SELECT`, `INSERT INTO`, `UPDATE`, and `DELETE`.
- **Data Types Supported**: `String`, `Integer`, `Double`, and `Boolean`.
- **Schema Display**: View table structures using the `.schema` command.
- **Autosave on Exit**: Program state is automatically saved when the program is closed.

## Getting Started
When you start MyDB, you will see the main menu:
```
-----CREATE DATABASE------
------LOAD DATABASE-------
-----DELETE DATABASE------
----------QUIT------------
```

To interact with the program, simply type the commands as displayed. Here are a few examples of valid inputs:
- `create database` ✅
- `CReaTe DaTabase` ✅
- `createdatabase` ❌ (invalid input)

## Commands

### Main Menu Commands
| Command             | Description                                   |
|---------------------|-----------------------------------------------|
| `CREATE DATABASE`   | Create a new database.                        |
| `LOAD DATABASE`     | Load an existing database from saved data.    |
| `DELETE DATABASE`   | Permanently delete an existing database.      |
| `QUIT`              | Save the current state and exit the program.  |

### Database Commands
After creating or loading a database, you will see the following menu:
```
-----------THIS IS MYDB-----------
---Enter 'quit' to exit program---
-------------COMMANDS:------------
-----------CREATE TABLE-----------
-----------INSERT INTO------------
-------------SELECT---------------
-------------UPDATE---------------
-------------DELETE---------------
-----------DROP TABLE-------------
--------------SAVE----------------
------------.schema---------------
--------------MENU----------------
```

### Table Commands
| Command             | Description                                              |
|---------------------|----------------------------------------------------------|
| `CREATE TABLE`      | Create a new table with specified columns and data types.|
| `DROP TABLE`        | Delete an entire table and its data.                     |

#### Data Types Supported
- `String`
- `Integer`
- `Double`
- `Boolean`

### Query Commands
| Command             | Description                                              |
|---------------------|----------------------------------------------------------|
| `INSERT INTO`       | Insert new data into a specified table.                  |
| `SELECT`            | Query data from a table with optional conditions.        |
| `UPDATE`            | Update existing data in a table based on conditions.     |
| `DELETE`            | Delete data from a table based on conditions.            |
| `.schema`           | Display the structure of all tables in the database.     |
| `SAVE`              | Save the current state of the program manually.          |
| `MENU`              | Return to the main menu to switch databases.             |

## Examples

### Creating a Table
```
Command: create table
Table: users
Insert column: id Integer
Insert column: name String
Insert column: age Integer
```

### Inserting Data
```
Command: insert into
Table: users
id: 1
name: John
age: 25
```

### Selecting Data
```
Command: select
Columns: name, age
From: users
Where: age > 20
```

Output:
```
+----+---+
|name|age|
+----+---+
|John| 25|
+----+---+
```

### Updating Data
```
Command: update
Table: users
SET Columns: name = Alice, age = 30
Where: name = John
```

Output:
```
+-----+---+
|name |age|
+-----+---+
|Alice| 30|
+-----+---+
```

### Deleting Data
```
Command: delete
From: users
Where: age < 31
```
Output:
```
+-----+---+
|name |age|   
+-----+---+  // Deletes Alice from table
+-----+---+
```

### Displaying Table Schema
```
Command: .schema
```

Output:
```
Table users:
id Integer,
name String,
age Integer
```


## Saving and Exiting
- **SAVE**: Use the `SAVE` command to manually save the program's state at any time.
- **QUIT**: Exiting the program will automatically save the current state, ensuring no data is lost.

## Notes
- Be cautious when using the `UPDATE` and `DELETE` commands without a `WHERE` clause, as it will affect all rows in the table.
- Use the `.schema` command to view the structure of your tables before performing any operations.

Feel free to explore and experiment with MyDB to manage your own databases!

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
