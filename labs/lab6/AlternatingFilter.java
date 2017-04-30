import java.util.Iterator;
import utils.Filter;

/** A kind of Filter that lets through every other VALUE element of
 *  its input sequence, starting with the first.
 *  @author You
 */
class AlternatingFilter<Value> extends Filter<Value> {

    /** A filter of values from INPUT that lets through every other
     *  value. */
    int count = 0;
    AlternatingFilter(Iterator<Value> input) {
        super(input); //FIXME?
        // FIXME
    }

    @Override
    protected boolean keep() {
        if (count % 2 == 0) {
            count += 1;
            return true;
        } 
        else {
            count += 1;
            return false;
        }
    }
}
