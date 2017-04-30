/** Represents an array of integers each in the range -8..7.
 *  Such integers may be represented in 4 bits (called nybbles).
 *  @author
 */
public class Nybbles {
    /** Return an array of size N. */
    public Nybbles(int N) {
        // DON'T CHANGE THIS.
        _data = new int[(N + 7) / 8];
        _n = N;
    }

    /** Return the size of THIS. */
    public int size() {
        return _n;
    }

    /** Return the Kth NYBBLE in THIS array, numbering from 0.
     *  Assumes 0 <= K < N. */
    public int get(int k) {
        if (k < 0 || k >= _n) {
            throw new IndexOutOfBoundsException();
        } else {
            // return _data[k];
            int count = 0;
            int result = 0;
            int current = 0;
            while (count >= 0) {
                if (count / 4 == k) {
                    current = _data[0] >> count;
                    break;
                } else {
                    count -= 1;
                }
            } for (int i = 4; i < 32; i += 1) {
                current = ((current >> (i)) & 0);
            } result = result ^ current;
            return result;
        }
    }

    /** Set the Kth integer in THIS array to VAL.  Assumes
     *  0 <= K < N and -8 <= VAL < 8. */
    public void set(int k, int val) {
        if (k < 0 || k >= _n) {
            throw new IndexOutOfBoundsException();
        } else if (val < -8 || val >= 8) {
            throw new IllegalArgumentException();
        } else {
            _data[k] = val;
        }
    }

    // DON'T CHANGE OR ADD TO THESE.
    /** Size of current array (in nybbles). */
    private int _n;
    /** The array data, packed 8 nybbles to an int. */
    private int[] _data;

    public static void main(String[] args) {
        Nybbles c = new Nybbles(6);
        System.out.println(c.get(0));
    }
}
