package db61b;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;

/** Tests the table class.
 *  @author Alexandra Dotterweich. */
public class testTable {

    /** Tests the constructor. */
    @Test
    public void testColumns() {
        String[] titles = {"Ones", "Twos"};
        Table r = new Table(titles);
        assertEquals(2, r.columns());
    }

    /** Tests get tile method. */
    @Test
    public void testGetTile() {
        Row r = new Row(new String[]{"Hello", "world"});
        assertEquals("world", r.get(1));
    }

    /** Tests add and size method. */
    @Test
    public void testAdd() {
        String[] titles = {"SID", "Lastname", "Firstname"};
        Table r = new Table(titles);
        Row bey = new Row(new String[]{"101", "Knowles", "Beyonce"});
        Row che = new Row(new String[]{"102", "Guevara", "Che"});
        Row pey = new Row(new String[]{"103", "Manning", "Peyton"});
        Row beybey = new Row(new String[]{"101", "Knowles", "Beyonce"});
        assertEquals(true, r.add(bey));
        assertEquals(true, r.add(che));
        assertEquals(true, r.add(pey));
        assertEquals(false, r.add(beybey));
        assertEquals(3, r.size());
    }

    /** Tests add and size method. */
    @Test
    public void testSize() {
        String[] titles = {"Ones", "Twos"};
        Table r = new Table(titles);
        Row w = new Row(new String[]{"q", "w"});
        Row t = new Row(new String[]{"H", "d"});
        Row f = new Row(new String[]{"l", "o"});
        r.add(w);
        r.add(t);
        r.add(f);
        assertEquals(3, r.size());
    }

    /** Tests find column method. */
    @Test
    public void testFindColumn() {
        String[] titles = {"SID", "Lastname", "Firstname"};
        Table r = new Table(titles);
        assertEquals(-1, r.findColumn("Birthday"));
        assertEquals(0, r.findColumn("SID"));
    }

    /** Tests select method with no conditions. */
    @Test
    public void testSelect() {
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("SID");
        colNames.add("Firstname");
        Row firstRow = new Row(new String[]{"101", "AKSOS", "wjw"});
        Table t = new Table(new String[]{"SID", "Lastname", "Firstname"});
        t.add(firstRow);
        Column c1 = new Column("SID", t);
        Column c2 = new Column("Lastname", t);
        Table h = t.select(colNames, null);
        Row expected = new Row(new String[] {"101", "wjw"});
        Iterator<Row> iter = h.iterator();
        Row actual = iter.next();
        assertEquals(expected, actual);
    }

    /** Tests equijoin method. */
    @Test
    public void testEquijoin() {
        boolean equal;
        boolean actual = false;
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("SID");
        Row firstRow = new Row(new String[]{"101"});
        Table t1 = new Table(new String[]{"SID"});
        t1.add(firstRow);
        Column c1 = new Column("SID", t1);
        List<Column> colList = new ArrayList<>();
        colList.add(c1);
        ArrayList<String> colNames2 = new ArrayList<String>();
        colNames.add("SID");
        Row firstRow2 = new Row(new String[]{"103"});
        Table t2 = new Table(new String[]{"SID"});
        t2.add(firstRow2);
        Column c2 = new Column("SID", t2);
        List<Column> colList2 = new ArrayList<>();
        colList2.add(c2);
        equal = Table.equijoin(colList, colList2, firstRow, firstRow2);
        assertEquals(equal, actual);

    }

    /** Main method that takes in ARGS. */
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(testTable.class));
    }
}
