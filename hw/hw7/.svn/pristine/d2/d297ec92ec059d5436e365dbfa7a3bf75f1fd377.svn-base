import java.util.LinkedList;
import java.util.ArrayList;

public class ECHashStringSet implements StringSet {
	
    private static final int loadFactor = 5;
    private int size;
    private int bucketCount;
    private LinkedList[] table;

    /** Constructs a linked list of the specified size and sets each of
      * those buckets to be a linked list. */
    public ECHashStringSet() {
        this.bucketCount = 1;
        table = new LinkedList[bucketCount];
        for (int i = 0; i < bucketCount; i += 1) {
            table[i] = new LinkedList();
        }
    }

    /** Returns the index for a given String. */
    public int hashGen(String s) {
        return ((s.hashCode() & 0x7fffffff) % bucketCount);
    }
    
    /** Adds the string S to the string set. If it is already present in the
      * set, do nothing.
      */
    public void put(String s) {
    	if (size/bucketCount > loadFactor) {
    		resize();
    	} 
    	table[hashGen(s)].add(s);
    }
 
    /** Resizes the array when it exceeds the loadFactor. */
    public void resize() {
    	LinkedList[] old = table;
    	LinkedList[] table = new LinkedList[bucketCount * 2];
        for (int i = 0; i < old.length; i += 1) {
        	for (int j = 0; j < old[i].size(); i +=1) {
        		LinkedList sublist =  table[i];
        		String item = (String) sublist.get(j);
        		put(item);
        	}
       }
    }

    /** Returns true if S is in the string set. */
    public boolean contains(String s) {
        return (containsHelper(s) != null);
    }

    /** Returns the String that is associated with the String at that value. */
    public String containsHelper(String s) {
        return (String) table[hashGen(s)].get(hashGen(s));
    }
}
