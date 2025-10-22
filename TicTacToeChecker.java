public class TicTacToeChecker {

    public int checkWin(char[][] board, char player) {
    for (int i = 0; i < 3; i++) {
        // Kiểm tra hàng
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
            return 1 + i; // 1, 2, 3 → tương ứng hàng 1–3
        // Kiểm tra cột
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            return 4 + i; // 4, 5, 6 → tương ứng cột 1–3
    }
    // Đường chéo chính
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
        return 7;
    // Đường chéo phụ
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        return 8;
    return 0; // chưa thắng
}

}
