import java.awt.*;
import javax.swing.*;

public class TicTacToeUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;
    private TicTacToeGame game = new TicTacToeGame();
    private final String colorBackground = "#FFDDAE"; 
    private final String colorBut = "#FBFBFB";
    private final String colorResetBut = "#fc9c9c";
    private final String colorHighLight = "#fc9c9c";
    

    public TicTacToeUI() {
        super("Tic Tac Toe 3x3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(Color.decode(colorBackground));
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boardPanel.setOpaque(false); // giữ nền trong suốt 
        boardPanel.setBackground(Color.decode(colorBackground));
        Font font = new Font("Monospaced", Font.BOLD, 48);
        
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                RoundedButton btn = new RoundedButton("");
                btn.setRadius(16);
                btn.setFont(font);
                btn.setFocusPainted(false);
                btn.setColor(Color.decode(colorBut));
                final int row = i, col = j;
                btn.addActionListener(e -> handleMove(row, col));
                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }
        
        add(boardPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout(6, 6));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        bottomPanel.setBackground(Color.decode(colorBackground));
        bottomPanel.setOpaque(false); 

        statusLabel = new JLabel("Turn: " + game.getCurrentPlayer(), SwingConstants.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.CENTER);

        RoundedButton resetBtn = new RoundedButton("Reset");
        resetBtn.setColor(Color.decode(colorResetBut));
        resetBtn.setRadius(16);
        resetBtn.addActionListener(e -> resetGame());
        bottomPanel.add(resetBtn, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleMove(int r, int c) {
        if (game.makeMove(r, c)) {
            updateBoard();
            if (game.isGameOver()) {
                if (game.getCurrentPlayer() == '-') {
                    statusLabel.setText("It's a draw!");
                } else {
                    statusLabel.setText("Player " + game.getCurrentPlayer() + " wins!");
                    highlightWinner(game.getWinningLine());
                }
            } else {
                statusLabel.setText("Turn: " + game.getCurrentPlayer());
            }
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                char c = game.getCell(i, j);
                buttons[i][j].setText(c == ' ' ? "" : String.valueOf(c));
            }
        }
    }

    // Highlight 3 ô thắng
    private void highlightWinner(int line) {
        Color winColor = Color.decode(colorHighLight);

        if (line >= 1 && line <= 3) { // hàng
            int row = line - 1;
            for (int j = 0; j < 3; j++)
                buttons[row][j].setBackground(winColor);
        } else if (line >= 4 && line <= 6) { // cột
            int col = line - 4;
            for (int i = 0; i < 3; i++)
                buttons[i][col].setBackground(winColor);
        } else if (line == 7) { // chéo chính
            for (int i = 0; i < 3; i++)
                buttons[i][i].setBackground(winColor);
        } else if (line == 8) { // chéo phụ
            for (int i = 0; i < 3; i++)
                buttons[i][2 - i].setBackground(winColor);
        }
    }

    private void resetGame() {
        game.reset();
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.decode(colorBut));
            }
        statusLabel.setText("Turn: " + game.getCurrentPlayer());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUI::new);
    }
}
