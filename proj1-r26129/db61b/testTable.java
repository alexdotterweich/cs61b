package db61b;
import org.junit.Test;
import static org.junit.Assert.*;

public class testTable {

    /** Tests the constructor */
    @Test
    public void testColumns() {
        String[] titles = {"Ones", "Twos"};
        Table r = new Table(titles);
        assertEquals(2, r.columns());
    }

    /** Tests get tile method */
    @Test
    public void testGetTile() {
        Row r = new Row(new String[]{"Hello", "world"});
        assertEquals("world", r.get(1));
    }

    /** Tests add and size method */
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

    /** Tests add and size method */
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

    /** Tests find column method */
    @Test
    public void testFindColumn() {
        String[] titles = {"SID", "Lastname", "Firstname"};
        Table r = new Table(titles);
        assertEquals(-1, r.findColumn("Birthday"));
        assertEquals(0, r.findColumn("SID"));
    }

    /** Tests equijoin method */
    @Test
    public void testEquijoin() {
        // Row r = new Row(new String[]{"Hello", "world"});
        // Row t = new Row(new String[]{"Hello", "world"});
        // Row f = new Row(new String[]{"World", "hello"});
        // assertEquals(true, r.equals(t));
        // assertEquals(false, r.equals(f));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(testTable.class));
    }
}