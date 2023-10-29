package edu.hw1.task8;

public final class KnightOnBoard {
    private final static short BOARD_SIZE = 8;
    private final static short LONG_MOVE = 2;
    private final static short SHORT_MOVE = 1;

    private KnightOnBoard() {
    }

    public static boolean canKillInOneMove(byte[][] board) {
        short[][] possibleMoves = {
            {SHORT_MOVE, -LONG_MOVE},
            {LONG_MOVE, -SHORT_MOVE},
            {LONG_MOVE, SHORT_MOVE},
            {SHORT_MOVE, LONG_MOVE},
            {-SHORT_MOVE, LONG_MOVE},
            {-LONG_MOVE, SHORT_MOVE},
            {-LONG_MOVE, -SHORT_MOVE},
            {-SHORT_MOVE, -LONG_MOVE}
        };
        if (board != null && board.length == BOARD_SIZE) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i] == null || board[i].length < BOARD_SIZE) {
                    throw new IllegalArgumentException("Nested array must not be null and its length must equal to 8");
                }
            }
        } else {
            throw new IllegalArgumentException("Array must not be null and its length must be equal to 8");
        }
        for (short x = 0; x < BOARD_SIZE; x++) {
            for (short y = 0; y < BOARD_SIZE; y++) {
                for (short move = 0; move < BOARD_SIZE; move++) {
                    short possibleX = possibleMoves[move][0];
                    short possibleY = possibleMoves[move][1];
                    if (board[x][y] == 1) {
                        if ((x + possibleX < BOARD_SIZE) && (x + possibleX > 0)
                            && (y + possibleY < BOARD_SIZE) && (y + possibleY > 0)
                            && board[x + possibleX][y + possibleY] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
