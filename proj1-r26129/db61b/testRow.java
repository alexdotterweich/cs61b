package db61b;
import org.junit.Test;
import static org.junit.Assert.*;

public class testRow {

    /** Tests size method */
    @Test
    public void testSize() {
        Row r = new Row(new String[]{"Hello", "world"});
        assertEquals(2, r.size());
    }

    /** Tests get method */
    @Test
    public void testGet() { 
        Row r = new Row(new String[]{"Hello", "world"});
        assertEquals("world", r.get(1));
    }

    /** Tests equals method */
    @Test
    public void testEquals() {
        Row r = new Row(new String[]{"Hello", "world"});
        Row t = new Row(new String[]{"Hello", "world"});
        Row f = new Row(new String[]{"World", "hello"});
        assertEquals(true, r.equals(t));
        assertEquals(false, r.equals(f));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(testRow.class));
    }
}