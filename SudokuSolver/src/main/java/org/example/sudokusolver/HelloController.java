package org.example.sudokusolver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Button solveButton;

    private static final int SIZE = 9;
    private TextField[][] cells = new TextField[SIZE][SIZE];

    @FXML
    public void initialize() {
        // Initialize the grid with TextFields
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                TextField textField = new TextField();
                textField.setPrefSize(50, 50);
                gridPane.add(textField, j, i);
                cells[i][j] = textField;
            }
        }

        // Set the action for the solve button
        solveButton.setOnAction(e -> solveSudoku());
    }

    private void solveSudoku() {
        int[][] board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = cells[i][j].getText();
                if (text.isEmpty()) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = Integer.parseInt(text);
                }
            }
        }

        if (solve(board)) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    cells[i][j].setText(Integer.toString(board[i][j]));
                }
            }
        } else {
            System.out.println("No solution exists");
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num ||
                    board[row - row % 3 + x / 3][col - col % 3 + x % 3] == num) {
                return false;
            }
        }
        return true;
    }
}