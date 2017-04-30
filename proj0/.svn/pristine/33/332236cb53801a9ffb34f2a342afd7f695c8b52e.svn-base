package game2048;
import ucb.util.CommandArgs;
import game2048.gui.Game;
import static game2048.Main.Side.*;

/** The main class for the 2048 game.
 *  @author Alexandra Dotterweich
 */
public class Main {

    /** Size of the board: number of rows and of columns. */
    static final int SIZE = 4;
    /** Number of squares on the board. */
    static final int SQUARES = SIZE * SIZE;

    /** Symbolic names for the four sides of a board. */
    static enum Side { NORTH, EAST, SOUTH, WEST };

    /** The main program.  ARGS may contain the options --seed=NUM,
     *  (random seed); --log (record moves and random tiles
     *  selected.); --testing (take random tiles and moves from
     *  standard input); and --no-display. */
    public static void main(String... args) {
        CommandArgs options =
            new CommandArgs("--seed=(\\d+) --log --testing --no-display",
                            args);
        if (!options.ok()) {
            System.err.println("Usage: java game2048.Main [ --seed=NUM ] "
                               + "[ --log ] [ --testing ] [ --no-display ]");
            System.exit(1);
        }

        Main game = new Main(options);

        while (game.play()) {
            /* No action */
        }
        System.exit(0);
    }

    /** A new Main object using OPTIONS as options (as for main). */
    Main(CommandArgs options) {
        boolean log = options.contains("--log"),
            display = !options.contains("--no-display");
        long seed = !options.contains("--seed") ? 0 : options.getLong("--seed");
        _testing = options.contains("--testing");
        _game = new Game("2048", SIZE, seed, log, display, _testing);
    }

    /** Reset the score for the current game to 0 and clear the board. */
    void clear() {
        _score = 0;
        _count = 0;
        _game.clear();
        _game.setScore(_score, _maxScore);
        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[r][c] = 0;
            }
        }
    }

    /** Play one game of 2048, updating the maximum score. Return true
     *  if play should continue with another game, or false to exit. */
    boolean play() {
        clear();
        while (true) {
            if (_count == 0) {
                setRandomPiece();
                setRandomPiece();
                _count = _count + 2;
            }
            if (_moved == true && _count != filledBoard) {
                setRandomPiece();
                _count++;
                _moved = false;
            }
            if (gameOver() == true) {
                _maxScore = Math.max(_score, _maxScore);
                _game.setScore(_score, _maxScore);
                _game.endGame();
            }
        GetMove:
            while (true) {
                String key = _game.readKey();
                if (key.equals("\u2191")) {
                    key = "Up";
                } else if (key.equals("\u2193")) {
                    key = "Down";
                } else if (key.equals("\u2190")) {
                    key = "Left";
                } else if (key.equals("\u2192")) {
                    key = "Right";
                }
                switch (key) {
                case "Up": case "Down": case "Left": case "Right":
                    if (!gameOver() && tiltBoard(keyToSide(key))) {
                        break GetMove;
                    } else {
                        if (tiltBoard(keyToSide(key)));
                            setRandomPiece();
                    }
                    break;
                case "Quit":
                    return false;

                case "New Game":
                    clear();
                    break GetMove;
                default:
                    break;
                }
            }
        }
    }


    int mergeChecker(int[][] board, int r, int c) {
        if (r > 3 || r < 0 || c > 3 || c < 0) {
            return -1;
        } else {
            return board[r][c];
        }
    }

    /** Return true if the current game is over (no more moves
     *  possible). */
    boolean gameOver() {
        if (_count == filledBoard || hasTwentyFourtyEight(_board)) {
            boolean canMerge = false;
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    if (_board[r][c] == mergeChecker(_board, r, c + 1)
                        || _board[r][c] == mergeChecker(_board, r, c - 1)
                        || _board[r][c] == mergeChecker(_board, r - 1, c)
                        || _board[r][c] == mergeChecker(_board, r + 1, c)) {
                        canMerge = true;
                    }
                }
            } if (canMerge) {
                return false;
            } return true;
        }
        return false;
    }

    boolean hasTwentyFourtyEight (int[][] board) {
        for (int f = 0; f < SIZE; f++) {
            for (int u = 0; u < SIZE; u++) {
                if (_board[f][u] == winning) {
                    return true;
                }
            }
        } return false;
    }

    /** Add a tile to a random, empty position, choosing a value (2 or
     *  4) at random.  Has no effect if the board is currently full. */
    void setRandomPiece() {
        if (_count == SQUARES) {
            return;
        }
        while (true) {
            int[] value = _game.getRandomTile();
            if (_board[value[1]][value[2]] == 0) {
                _game.addTile(value[0], value[1], value[2]);
                _board[value[1]][value[2]] = value[0];
                break;
            }
        }
    }

    /** Returns the amount of 0's between the current tile and either the
     * top of the board or the next tile. */
    int distance(int[][] board, int r, int c) {
        int count = 0;
        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] != 0) {
                return count;
            }
            count++;
        } return count;
    }

    /** Perform the result of tilting the board toward SIDE.
     *  Returns true iff the tilt changes the board. **/
    boolean tiltBoard(Side side) {
        /* As a suggestion (see the project text), you might try copying
         * the board to a local array, turning it so that edge SIDE faces
         * north.  That way, you can re-use the same logic for all
         * directions.  (As usual, you don't have to). */
        int[][] board = new int[SIZE][SIZE];

        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                board[r][c] =
                    _board[tiltRow(side, r, c)][tiltCol(side, r, c)];
            }
        }
        for (int r = 1; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                boolean hasMerged = false;
                if (board[r][c] != 0) {
                    int dist = distance(board, r, c);
                    int row = tiltRow(side, r, c);
                    int col = tiltCol(side, r, c);
                    int i = r;
                    while (i > 0 && board[i - 1][c] == 0) {
                        i--;
                    }
                    if (i > 0 && hasMerged == false && board[r][c] == board[i - 1][c]) {
                        _game.mergeTile(board[r][c], (board[r][c] * 2), row, col,
                                    tiltRow(side, i - 1, c), tiltCol(side, i - 1, c));
                        board[i - 1][c] = (board[r][c] * 2) + 1;
                        board[r][c] = 0;
                        hasMerged = true;
                        _count--;
                        _score += board[i - 1][c] - 1;
                        _moved = true;
                    } else if (distance(board, r, c) > 0) {
                        _game.moveTile(board[r][c], tiltRow(side, r, c), tiltCol(side, r, c),
                                tiltRow(side, (r - dist), c), tiltCol(side, (r - dist), c));
                        board[r - dist][c] = board[r][c];
                        board[r][c] = 0;
                        _moved = true;
                    }
                }
            }
        }
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] % 2 != 0) {
                    board[r][c] = board[r][c] - 1;
                }
            }
        }
        _game.setScore(_score, _maxScore);
        _game.displayMoves();

        for (int r = 0; r < SIZE; r += 1) {
            for (int c = 0; c < SIZE; c += 1) {
                _board[tiltRow(side, r, c)][tiltCol(side, r, c)]
                    = board[r][c];
            }
        }
        return true;
    }


    /** Return the row number on a playing board that corresponds to row R
     *  and column C of a board turned so that row 0 is in direction SIDE (as
     *  specified by the definitions of NORTH, EAST, etc.).  So, if SIDE
     *  is NORTH, then tiltRow simply returns R (since in that case, the
     *  board is not turned).  If SIDE is WEST, then column 0 of the tilted
     *  board corresponds to row SIZE - 1 of the untilted board, and
     *  tiltRow returns SIZE - 1 - C. */
    int tiltRow(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return r;
        case EAST:
            return c;
        case SOUTH:
            return SIZE - 1 - r;
        case WEST:
            return SIZE - 1 - c;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the column number on a playing board that corresponds to row
     *  R and column C of a board turned so that row 0 is in direction SIDE
     *  (as specified by the definitions of NORTH, EAST, etc.). So, if SIDE
     *  is NORTH, then tiltCol simply returns C (since in that case, the
     *  board is not turned).  If SIDE is WEST, then row 0 of the tilted
     *  board corresponds to column 0 of the untilted board, and tiltCol
     *  returns R. */
    int tiltCol(Side side, int r, int c) {
        switch (side) {
        case NORTH:
            return c;
        case EAST:
            return SIZE - 1 - r;
        case SOUTH:
            return SIZE - 1 - c;
        case WEST:
            return r;
        default:
            throw new IllegalArgumentException("Unknown direction");
        }
    }

    /** Return the side indicated by KEY ("Up", "Down", "Left",
     *  or "Right"). */
    Side keyToSide(String key) {
        switch (key) {
        case "Up":
            return NORTH;
        case "Down":
            return SOUTH;
        case "Left":
            return WEST;
        case "Right":
            return EAST;
        default:
            throw new IllegalArgumentException("unknown key designation");
        }
    }
    private final int winning = 2048;
    private final int filledBoard = 16;
    /** If tiles are moved if the user hits a key, this gets set to true. */
    private boolean _moved = false;
    /** Represents the board: _board[r][c] is the tile value at row R,
     *  column C, or 0 if there is no tile there. */
    private final int[][] _board = new int[SIZE][SIZE];
    /** True if --testing option selected. */
    private boolean _testing;
    /** The current input source and output sink. */
    private Game _game;
    /** The score of the current game, and the maximum final score
     *  over all games in this session. */
    private int _score, _maxScore;
    /** Number of tiles on the board. */
    private int _count;
}
