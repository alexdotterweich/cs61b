package jump61;

import java.util.ArrayList;
import java.util.Stack;

import static jump61.Side.*;
import static jump61.Square.square;

/** A Jump61 board state that may be modified.
 *  @author Alex Dotterweich
 */
class MutableBoard extends Board {

    /** An N x N board in initial configuration. */
    MutableBoard(int N) {
        if (N > 1 && N <= 10) {
            for (int r = 0; r < N * N; r += 1) {
                Square i = square(WHITE, 1);
                _boardElems.add(i);
            }
            markUndo();
        } else {
            GameException.error("Board size is too small.");
        }
    }

    /** A board whose initial contents are copied from BOARD0, but whose
     *  undo history is clear. */
    MutableBoard(Board board0) {
        for (int i = 0; i < Math.pow(board0.size(), 2); i += 1) {
            _boardElems.add(i, board0.get(i));
        }
    }

    @Override
    void clear(int N) {
        _boardElems.clear();
        if (N > 1 && N <= 10) {
            _boardElems = new ArrayList<Square>();
            for (int r = 0; r < N * N; r += 1) {
                Square i = square(WHITE, 1);
                _boardElems.add(i);
            }
            redCount = 0;
            blueCount = 0;
        } else {
            GameException.error("Board size is too small.");
        }
        announce();
    }

    @Override
    void copy(Board board) {
        for (int i = 0; i < Math.pow(board.size(), 2); i += 1) {
            _boardElems.add(i, board.get(i));
        }
    }

    @Override
    int size() {
        return (int) Math.sqrt(_boardElems.size());
    }

    @Override
    Square get(int n) {
        return _boardElems.get(n);
    }

    @Override
    int numOfSide(Side side) {
        int numSide = 0;
        for (Square i : _boardElems) {
            if (i.getSide().equals(side)) {
                numSide += 1;
            }
        } return numSide;
    }

    @Override
    int numPieces() {
        int numSpots = 0;
        for (Square i : _boardElems) {
            numSpots += i.getSpots();
        } return numSpots;
    }

    @Override
    void addSpot(Side player, int r, int c) {
        if (isLegal(player)) {
            addSpot(player, sqNum(r, c));
        }
        announce();
    }

    @Override
    void addSpot(Side player, int n) {
        Square origSq = _boardElems.get(n);
        Side who = origSq.getSide();
        if (who == player || who == WHITE) {
            addSpotHelper(player, n);
        }
        blueCount = 0;
        redCount = 0;
        for (Square i : _boardElems) {
            if (i.getSide() == BLUE) {
                blueCount += 1;
            } else if (i.getSide() == RED) {
                redCount += 1;
            }
        }
        preventInfiniteLoop = 0;
        markUndo();
        announce();
    }

    /** Add a spot from PLAYER at square N. */
    void addSpotHelper(Side player, int n) {
        Square origSq = _boardElems.get(n);
        int spots = origSq.getSpots();
        if (spots == neighbors(n)) {
            Square restartSq = Square.square(player, 1);
            _boardElems.set(n, restartSq);
            preventInfiniteLoop += 1;
            if (numOfSide(RED) == 0
                    || numOfSide(RED) == Math.pow(size(), 2)) {
                return;
            }
            getNeighbor(player, n);
        } else {
            int origSqSC = origSq.getSpots() + 1;
            Square newOrigSq = Square.square(player, origSqSC);
            _boardElems.set(n, newOrigSq);
        }
    }

    /** Adds spots from PLAYER to neighboring squares
      * if square N becomes over-full. */
    void getNeighbor(Side player, int n) {
        int size = size();
        if (row(n) > 1) {
            addSpotHelper(player, n - size);
        }
        if (col(n) > 1) {
            addSpotHelper(player, n - 1);
        }
        if (row(n) < size) {
            addSpotHelper(player, n + size);
        }
        if (col(n) < size) {
            addSpotHelper(player, n + 1);
        }
    }

    @Override
    void set(int r, int c, int num, Side player) {
        internalSet(sqNum(r, c), square(player, num));
    }

    @Override
    void set(int n, int num, Side player) {
        if (player == BLUE) {
            blueCount += 1;
        }
        if (player == RED) {
            redCount += 1;
        }
        internalSet(n, square(player, num));
        announce();
    }

    @Override
    void undo() {
        boardStack.pop();
        MutableBoard newBoard = boardStack.peek();
        copy(newBoard);
        _boardElems = newBoard._boardElems;
    }

    /** Record the beginning of a move in the undo history. */
    private void markUndo() {
        ConstantBoard C = new ConstantBoard(this);
        MutableBoard D = new MutableBoard(C);
        boardStack.push(D);
    }

    /** Set the contents of the square with index IND to SQ. Update counts
     *  of numbers of squares of each color.  */
    private void internalSet(int ind, Square sq) {
        _boardElems.set(ind, sq);
    }

    /** Notify all Observers of a change. */
    private void announce() {
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MutableBoard)) {
            return obj.equals(this);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    /** Returns number of red squares on board. */
    public int redCountReturn() {
        return redCount;
    }

    /** Returns number of blue squares on board. */
    public int blueCountReturn() {
        return blueCount;
    }

    /** Helps to prevent infinite loops. */
    private int preventInfiniteLoop = 1;

    /** Number of red squares on board. */
    private int redCount = 0;

    /** Number of blue squares on board. */
    private int blueCount = 0;

    /** Stack of ConstantBoard objects. */
    private Stack<MutableBoard> boardStack = new Stack<MutableBoard>();

    /** ArrayList of squares that represents the board. */
    private ArrayList<Square> _boardElems = new ArrayList<Square>();

}
