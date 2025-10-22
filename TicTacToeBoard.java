public class TicTacToeBoard {
    private char[][] board;

    public TicTacToeBoard() {
        board = new char[3][3];
        reset();
    }

    public void reset() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = ' ';
    }

    // CREATE/UPDATE
    public boolean setCell(int r, int c, char value) {
        if (r < 0 || r >= 3 || c < 0 || c >= 3) return false;
        if (board[r][c] != ' ') return false;
        board[r][c] = value;
        return true;
    }

    // READ
    public char getCell(int r, int c) {
        return board[r][c];
    }

    // DELETE (xóa ô)
    public void clearCell(int r, int c) {
        board[r][c] = ' ';
    }

    public boolean isFull() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == ' ') return false;
        return true;
    }

    public char[][] getBoardCopy() {
        char[][] copy = new char[3][3];
        for (int i = 0; i < 3; i++)
            System.arraycopy(board[i], 0, copy[i], 0, 3);
        return copy;
    }
}
