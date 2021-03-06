package jump61;

import java.util.ArrayList;

/** An automated Player.
 *  @author Alex Dotterweich
 */
class AI extends Player {

    /** Time allotted to all but final search depth (milliseconds). */
    private static final long TIME_LIMIT = 15000;

    /** Number of calls to minmax between checks of elapsed time. */
    private static final long TIME_CHECK_INTERVAL = 10000;

    /** Number of milliseconds in one second. */
    private static final double MILLIS = 1000.0;

    /** A new player of GAME initially playing COLOR that chooses
     *  moves automatically.
     */
    AI(Game game, Side color) {
        super(game, color);
    }

    @Override
    void makeMove() {
        Board b = getBoard();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] n = minmax(this.getSide(), b, 4, (int) lostGame,
                (int) wonGame, list);
        getGame().reportMove(this.getSide(), b.row(n[0]), b.col(n[0]));
        getGame().makeMove(n[0]);
    }

    /** Return the minimum of CUTOFF and the minmax value of board B
     *  (which must be mutable) for player P to a search depth of D
     *  (where D == 0 denotes statically evaluating just the next move).
     *  If MOVES is not null and CUTOFF is not exceeded, set MOVES to
     *  a list of all highest-scoring moves for P; clear it if
     *  non-null and CUTOFF is exceeded. the contents of B are
     *  invariant over this call. Used ALPHA and BETA instead of cutoff.
     *  Conceptually discussed with James Casanova after being stuck
     *  on my recursive call. */
    private int[] minmax(Side p, Board b, int d, int alpha, int beta,
                       ArrayList<Integer> moves) {
        ArrayList<Integer> lM = legalMoves(b, p);
        int bestSoFar = staticEval(p, b);
        int bestI = -1;
        int[] bestMoves = new int[2];
        bestMoves[0] = bestI;
        bestMoves[1] = bestSoFar;
        Side ai = getSide();
        if (b.getWinner() == ai) {
            bestMoves[1] = ONE_HUNDRED;
            return bestMoves;
        } else if (b.getWinner() == ai.opposite()) {
            bestMoves[1] = NEG_ONE_HUNDRED;
            return bestMoves;
        } else if (d == 0) {
            bestSoFar = staticEval(p, b);
            bestMoves[1] = bestSoFar;
            return bestMoves;
        }
        for (int i : lM) {
            MutableBoard next = new MutableBoard(b);
            next.addSpot(p, i);
            int[] best = minmax(p.opposite(), next, d - 1, alpha, beta, moves);
            bestSoFar = best[1];
            if (p == getSide()) {
                if (alpha < bestSoFar) {
                    alpha = bestSoFar;
                    bestMoves[0] = i;
                }
                if (beta <= alpha) {
                    bestMoves[1] = alpha;
                }
            } else {
                if (beta > bestSoFar) {
                    beta = bestSoFar;
                    bestMoves[0] = i;
                }
                if (beta <= alpha) {
                    bestMoves[0] = i;
                    break;
                }
            }
        }
        if (p == getSide()) {
            bestMoves[1] = alpha;
        } else {
            bestMoves[1] = beta;
        }
        return bestMoves;
    }

    /** Returns an iterable list of all legal moves for the AI.
     * B is the current board and P is the player who's making
     * the moves on said board.
     * Discussed using such a method with James Casanova in an
     * attempt to fix a weird bug where my size became 0. My bug
     * ended up being with my size method itself however. */
    private ArrayList<Integer> legalMoves(Board b, Side p) {
        ArrayList<Integer> legalMoveList = new ArrayList<Integer>();
        for (int i = 0; i < Math.pow(b.size(), 2); i += 1) {
            if (b.isLegal(p, i)) {
                legalMoveList.add(i);
            }
        }
        return legalMoveList;
    }

    /** Returns heuristic value of board B for player P.
     *  Higher is better for P. */
    private int staticEval(Side p, Board b) {
        int score = b.numOfSide(this.getSide());
        int otherScore = b.numOfSide(this.getSide().opposite());
        return score - otherScore;
    }

    /** Represents 100. */
    static final int ONE_HUNDRED = 100;

    /** Represents -100. */
    static final int NEG_ONE_HUNDRED = -100;

    /** Arbitrarily large number to start off as the beta. */
    private double wonGame = Double.POSITIVE_INFINITY;

    /** Arbitrarily small number to start off as the alpha. */
    private double lostGame = Double.NEGATIVE_INFINITY;

}
