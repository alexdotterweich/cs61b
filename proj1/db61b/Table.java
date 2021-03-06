package db61b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static db61b.Utils.*;

/** A single table in a database.
 *  @author P. N. Hilfinger
 */
class Table implements Iterable<Row> {
    /** A new Table whose columns are given by COLUMNTITLES, which may
     *  not contain dupliace names. */
    Table(String[] columnTitles) {
        for (int i = columnTitles.length - 1; i >= 1; i -= 1) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (columnTitles[i].equals(columnTitles[j])) {
                    throw error("duplicate column name: %s",
                                columnTitles[i]);
                }
            }
        }
        myColumns = columnTitles;
    }

    /** A new Table whose columns are give by COLUMNTITLES. */
    Table(List<String> columnTitles) {
        this(columnTitles.toArray(new String[columnTitles.size()]));
    }

    /** Return the number of columns in this table. */
    public int columns() {
        return myColumns.length;
    }

    /** Return the title of the Kth column.  Requires 0 <= K < columns(). */
    public String getTitle(int k) {
        if (k >= 0 && k < columns()) {
            return myColumns[0];
        }
        throw new ArrayIndexOutOfBoundsException("invalid column selection");
    }

    /** Return the number of the column whose title is TITLE, or -1 if
     *  there isn't one. */
    public int findColumn(String title) {
        for (int i = 0; i < myColumns.length; i += 1) {
            if (myColumns[i].equals(title)) {
                return i;
            }
        }
        return -1;
    }

    /** Return the number of Rows in this table. */
    public int size() {
        return _rows.size();
    }

    /** Returns an iterator that returns my rows in an unspecfied order. */
    @Override
    public Iterator<Row> iterator() {
        return _rows.iterator();
    }

    /** Add ROW to THIS if no equal row already exists.  Return true if anything
     *  was added, false otherwise. */
    public boolean add(Row row) {
        for (int i = 0; i < _rows.size(); i += 1) {
            if (_rows.contains(row)) {
                return false;
            }
        }
        _rows.add(row);
        return true;
    }

    /** Read the contents of the file NAME.db, and return as a Table.
     *  Format errors in the .db file cause a DBException. */
    static Table readTable(String name) {
        BufferedReader input;
        Table table;
        input = null;
        table = null;
        try {
            input = new BufferedReader(new FileReader(name + ".db"));
            String header = input.readLine();
            if (header == null) {
                throw error("missing header in DB file");
            }
            String[] columnNames = header.split(",");
            table = new Table(columnNames);
            header = input.readLine();
            while (header != null) {
                String[] result = header.split(",");
                Row r = new Row(result);
                table.add(r);
                header = input.readLine();
            }
        } catch (FileNotFoundException e) {
            throw error("could not find %s.db", name);
        } catch (IOException e) {
            throw error("problem reading from %s.db", name);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
        return table;
    }

    /** Prints out each row in the hash set and takes in P. */
    public void writeRows(PrintStream p) {
        for (Row r : _rows) {
            System.out.printf(r.toString());
        }
    }

    /** Prints out each column in the string array and takes in P. */
    public void writeColumns(PrintStream p) {
        for (int i = 0; i < myColumns.length; i += 1) {
            System.out.printf(myColumns[i]);
        }
    }

    /** Write the contents of TABLE into the file NAME.db. Any I/O errors
     *  cause a DBException. */
    void writeTable(String name) {
        PrintStream output;
        output = null;
        try {
            String sep;
            sep = "";
            output = new PrintStream(name + ".db");
            writeColumns(output);
            writeRows(output);
        } catch (IOException e) {
            throw error("trouble writing to %s.db", name);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /** Print my contents on the standard output. */
    void print() {
        for (Row r : _rows) {
            System.out.println(r.formatPrint());
        }
    }

    /** Return a new Table whose columns are COLUMNNAMES, selected from
     *  rows of this table that satisfy CONDITIONS.
     *  Initially, I was running into problems using a while loop in an
     *  attempt to iterate over the _rows, so James Casanova suggested I
     *  create a row iterator. */
    Table select(List<String> columnNames, List<Condition> conditions) {
        Table result = new Table(columnNames);
        ArrayList<Column> listCols = new ArrayList<Column>();
        for (String i : columnNames) {
            Column c = new Column(i, this);
            listCols.add(c);
        }
        Iterator<Row> iterate = _rows.iterator();
        if (conditions == null) {
            for (Row r : _rows) {
                Row ii = new Row(listCols, r);
                result.add(ii);
            }
        } else {
            Iterator<Row> rowIter = this.iterator();
            for (int i = 0; i < this.size(); i += 1) {
                Row row = rowIter.next();
                if (Condition.test(conditions, row)) {
                    Row iii = new Row(listCols, row);
                    result.add(iii);
                }
            }
        }
        return result;
    }

    /** Return a new Table whose columns are COLUMNNAMES, selected
     *  from pairs of rows from this table and from TABLE2 that match
     *  on all columns with identical names and satisfy CONDITIONS. */
    Table select(Table table2, List<String> columnNames,
                 List<Condition> conditions) {
        Table result = new Table(columnNames);
        Table selectFrom1;
        Table selectFrom2;
        ArrayList<Column> equalsCols1 = new ArrayList<Column>();
        ArrayList<Column> equalsCols2 = new ArrayList<Column>();
        ArrayList<String> colNames1 = new ArrayList<String>();
        ArrayList<String> colNames2 = new ArrayList<String>();
        for (String col1 : myColumns) {
            for (String col2 : columnNames) {
                if (col1.equals(col2)) {
                    Column c1 = new Column(col1, this);
                    Column c2 = new Column(col2, table2);
                    colNames1.add(col1);
                    colNames2.add(col2);
                    equalsCols1.add(c1);
                    equalsCols2.add(c2);
                }
            }
        }
        selectFrom1 = select(colNames1, conditions);
        selectFrom2 = select(colNames2, conditions);
        return selectFrom1;
    }

    /** Return true if the columns COMMON1 from ROW1 and COMMON2 from
     *  ROW2 all have identical values.  Assumes that COMMON1 and
     *  COMMON2 have the same number of elements and the same names,
     *  that the columns in COMMON1 apply to this table, those in
     *  COMMON2 to another, and that ROW1 and ROW2 come, respectively,
     *  from those tables. */
    public static boolean equijoin(List<Column> common1, List<Column> common2,
                                    Row row1, Row row2) {
        Iterator<Column> colIter1 = common1.iterator();
        Iterator<Column> colIter2 = common2.iterator();
        for (int i = 0; i < row1.size(); i += 1) {
            for (int j = 0; j < row2.size(); j += 1) {
                colIter1.next();
                colIter2.next();
                if (!row1.get(i).equals(row2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /** My rows. */
    private HashSet<Row> _rows = new HashSet<>();

    /** Column names are stored here. */
    private String[] myColumns;

}
