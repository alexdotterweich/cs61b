package db61b;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;

/** A single row of a database.
 *  @author Alex Dotterweich
 */
class Row {
    /** A Row whose column values are DATA.  The array DATA must not be altered
     *  subsequently. */
    Row(String[] data) {
        _data = data;
    }

    /** Given N colums and rows, returns a new row containing one column from
     *  each of the supplied ROWS.
     *
     *  The value for the ith column of this new row will come from the ith row
     *  in ROWS, using the ith entry of COLUMNS as an effective column index.
     *
     *  There is a method in the Columns class that you'll need to use,
     *  see {@link db61b.Column#getFrom}).  you're wondering why this looks like
     *  a potentially clickable link it is! Just not in source. You might
     *  consider converting this spec to HTML using the Javadoc command.
     */
    Row(List<Column> columns, Row... rows) {
        // FILL IN
    }

    /** Return my number of columns. */
    int size() {
        return _data.length;
    }

    /** Prints each element in the hashset with commas. */
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i += 1) {
            result += i;
            if (i != size() - 1) {
                result += ",";
            }
        }
        return result;
    }

    /** Prints each element in the hashset with spaces. */
    public String formatPrint() {
        String result = "    ";
        for (int i = 0; i < size(); i += 1) {
            result += i;
            if (i != size() - 1) {
                result += " ";
            }
        }
        return result;
    }

    /** Return the value of my Kth column.  Requires that 0 <= K < size(). */
    String get(int k) {
        if (k >= 0 && k < size()) {
            return _data[k];
        }
        throw new ArrayIndexOutOfBoundsException("invalid row selection");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Row) {
            Row o = ((Row) obj);
            if (o.size() == this.size()) {
                for (int i = 0; i < this.size(); i += 1) {
                    if (this.get(i) == o.get(i)) {
                        continue;
                    }
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /* NOTE: Whenever you override the .equals() method for a class, you
     * should also override hashCode so as to insure that if
     * two objects are supposed to be equal, they also return the same
     * .hashCode() value (the converse need not be true: unequal objects MAY
     * also return the same .hashCode()).  The hash code is used by certain
     * Java library classes to expedite searches (see Chapter 7 of Data
     * Structures (Into Java)). */

    @Override
    public int hashCode() {
        return Arrays.hashCode(_data);
    }

    /** Contents of this row. */
    private String[] _data;
}
