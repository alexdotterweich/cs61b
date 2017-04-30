
public class BSTStringSet implements StringSet{
	  public Node datum;

	    public class Node {
	        public String s;
	        public Node left, right;


	        public Node(String s) {
	            this.s = s;
	        }

	    }
	    /** Adds the string S to the string set. If it is already present in the
	      * set, do nothing.
	      */
	    public void put(String s) {
	        datum = putHelper(datum, s);
	    }

	    public Node putHelper(Node n, String s) {
	        if (n == null) {
	            return new Node(s);
	        } if (s.compareTo(n.s) > 0) {
	            return putHelper(n.right, s);
	        } if (s.compareTo(n.s) < 0) {
	            return putHelper(n.left, s); 
	        } else {
	            n.s = s;
	            return n;
	        }
	    }

	    /** Returns true if S is in the string set. */
	    public boolean contains(String s) {
	        if (containsFacilitator(s) == null) {
	            return false;
	        }
	        return true;
	    }

	    /** Returns the result of calling the helper but passing in the datum
	      * so it can traverse the whole tree. */
	    public String containsFacilitator(String s) {
	        return containsHelper(datum, s);
	    }

	    /** Returns the string if it's in the tree, null otherwise. */
	    public String containsHelper(Node n, String s) {
	        if (n == null) {
	            return null;
	        } if (s.compareTo(n.s) > 0) {
	            return containsHelper(n.right, s);
	        } if (s.compareTo(n.s) < 0) {
	            return containsHelper(n.left, s); 
	        } else {
	            return n.s;
	        }
	    }


}
