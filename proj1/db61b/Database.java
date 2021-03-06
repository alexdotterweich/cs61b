package db61b;
import java.util.Hashtable;

/** A collection of Tables, indexed by name.
 *  @author Alexandra Dotterweich. */
class Database {
    /** An empty database. */
    public Database() {
        _database = new Hashtable<String, Table>();
    }

    /** Return the Table whose name is NAME stored in this database, or null
     *  if there is no such table. */
    public Table get(String name) {
        return _database.get(name);
    }

    /** Set or replace the table named NAME in THIS to TABLE.  TABLE and
     *  NAME must not be null, and NAME must be a valid name for a table. */
    public void put(String name, Table table) {
        if (name == null || table == null) {
            throw new IllegalArgumentException("null argument");
        }
        _database.put(name, table);
    }
    /** Stores the tables into a hashtable. */
    private Hashtable<String, Table> _database;
}
