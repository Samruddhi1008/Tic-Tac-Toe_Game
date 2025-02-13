import java.util.Scanner;

public class TicTacToe {

    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        boolean gameWon = false;

        while (!gameWon && !isBoardFull()) {
            playerMove();
            printBoard();
            gameWon = checkWin();
            if (!gameWon) {
                switchPlayer();
            }
        }

        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("The game is a draw.");
        }
    }

    // Initialize the Tic-Tac-Toe board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Display the current board
    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Handle the player's move
    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column (1-3): ");
            row = scanner.nextInt() - 1; // Subtracting 1 to make it zero-indexed
            col = scanner.nextInt() - 1;

            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("This move is not valid.");
            }
        }
    }

    // Switch to the other player
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if the current player has won
    private static boolean checkWin() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check if any row has all the same marks
    private static boolean checkRows() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check if any column has all the same marks
    private static boolean checkColumns() {
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check both diagonals
    private static boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer));
    }

    // Check if the board is full (i.e., no more moves possible)
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
