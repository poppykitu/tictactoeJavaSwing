public class TicTacToeGame {
    private TicTacToeBoard board;
    private TicTacToeChecker checker;
    private char currentPlayer;
    private boolean gameOver;
    private int winningLine = 0;

    public TicTacToeGame() {
        board = new TicTacToeBoard();
        checker = new TicTacToeChecker();
        reset();
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean makeMove(int r, int c) {
        if (gameOver) return false;
        if (!board.setCell(r, c, currentPlayer)) return false;

        // ✅ Lưu lại kết quả checkWin
        winningLine = checker.checkWin(board.getBoardCopy(), currentPlayer);

        if (winningLine != 0) {
            gameOver = true;
        } else if (board.isFull()) {
            gameOver = true;
            currentPlayer = '-'; // hòa
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;
    }

    public int getWinningLine() {
        return winningLine;
    }

    public char getCell(int r, int c) {
        return board.getCell(r, c);
    }

    public void reset() {
        board.reset();
        currentPlayer = 'X';
        gameOver = false;
        winningLine = 0;
    }
}
