public class DoubleChain {
    public DoubleChain prev;
    public DoubleChain next;
    public double val;
    public DoubleChain(double value) {
        prev = null;
        next = null;
        val = value;
    }
    public static DoubleChain getBack(DoubleChain d) {
        while (d.next != null) {
            d = d.next;
        }
        return d;

    }
    public static DoubleChain getFront(DoubleChain d) {
        while (d.prev != null) {
            d = d.prev;
        }
        return d;
    }
    public static void insertBack(DoubleChain d, double val) {
        DoubleChain x = getBack(d);
        x.next = new DoubleChain(val);
        x.next.prev = x;
    }
    public static void insertFront(DoubleChain d, double val) {
        DoubleChain x = getFront(d);
        x.prev = new DoubleChain(val);
        x.prev.next = x;
    }
    public static String toString(DoubleChain d) {
        return null;
    }

}