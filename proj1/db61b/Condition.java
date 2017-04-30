package db61b;

import java.util.List;

/** Represents a single 'where' condition in a 'select' command.
 *  @author Alex Dotterweich. */
class Condition {

    /** A Condition representing COL1 RELATION COL2, where COL1 and COL2
     *  are column designators. and RELATION is one of the
     *  strings "<", ">", "<=", ">=", "=", or "!=". */
    Condition(Column col1, String relation, Column col2) {
        _col1 = col1;
        _col2 = col2;
        _relation = relation;
    }

    /** A Condition representing COL1 RELATION 'VAL2', where COL1 is
     *  a column designator, VAL2 is a literal value (without the
     *  quotes), and RELATION is one of the strings "<", ">", "<=",
     *  ">=", "=", or "!=".
     */
    Condition(Column col1, String relation, String val2) {
        this(col1, relation, (Column) null);
        _val2 = val2;
    }

    /** Assuming that ROWS are rows from the respective tables from which
     *  my columns are selected, returns the result of performing the test I
     *  denote.
     *  To debug my select with literals problem, I wrote print statements,
     *  and after I narrowed the bug down to be something ary in how I was
     *  calling .compareTo, Carlos Flores looked at those print statments,
     *  and noted that I was passing in the switcher value with quotes. */
    boolean test(Row... rows) {
        _value = _col1.getFrom(rows);
        String switcher = _val2;
        if (_col2 != null) {
            switcher = _col2.getFrom(rows);
        }
        if (_relation.equals("<")) {
            return (_value.compareTo(switcher) < 0);
        }
        if (_relation.equals(">")) {
            if (_value.compareTo(switcher) > 0) {
                return true;
            }
        }
        if (_relation.equals("<=")) {
            return (_value.compareTo(switcher) <= 0);
        }
        if (_relation.equals(">=")) {
            return (_value.compareTo(switcher) >= 0);
        }
        if (_relation.equals("=")) {
            if (_value.compareTo(switcher) == 0) {
                return true;
            }
        }
        if (_relation.equals("!=")) {
            if (_value.compareTo(switcher) != 0) {
                return true;
            }
        }
        return false;
    }


    /** Return true iff ROWS satisfies all CONDITIONS. */
    static boolean test(List<Condition> conditions, Row... rows) {
        for (Condition cond : conditions) {
            if (!cond.test(rows)) {
                return false;
            }
        }
        return true;
    }

    /** The operands of this condition.  _col2 is null if the second operand
     *  is a literal. */
    private Column _col1, _col2;
    /** Second operand, if literal (otherwise null). */
    private String _val2;
    /** Second operand, represents a relation. */
    private String _relation;
    /** The value at a specific row. */
    private String _value;
}
