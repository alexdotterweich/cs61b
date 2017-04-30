/** An alternative addition procedure based on bit operations.
 *  without using the operators +, -, /, *, or \%, ++, --, +=, -=, *=, \=,
 *  %=, or any method calls. Instead, use the bitwise operators &, |, ^, ~,
 *  <<, >>, >>>, and &=, etc.
 *  @author Alex Dotterweih
 */
public class Adder {
    /** Returns X+Y. */
    public static int add(int x, int y) {
        /* FILL IN */
        int result = 0;
        int carry = 0;
        for (int i = 0; i < 32; i += 1) {
            /* FILL IN */
            int xbit = x & (1<<(i));
            int ybit = y & (1<<(i));
            int rbit = xbit ^ ybit;
            rbit = rbit ^ carry;
            carry = ((xbit & ybit) | (xbit & carry) | (ybit & carry))<<(1);
            result = result ^ rbit;
        }

        /* REPLACE WITH SOMETHING THAT WORKS. */
        return result;
    }

}
