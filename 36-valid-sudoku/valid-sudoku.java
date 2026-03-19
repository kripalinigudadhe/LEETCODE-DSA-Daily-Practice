import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        // Check rows and columns
        for (int i = 0; i < 9; i++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                
                // Row check
                if (board[i][j] != '.') {
                    if (rowSet.contains(board[i][j])) return false;
                    rowSet.add(board[i][j]);
                }

                // Column check
                if (board[j][i] != '.') {
                    if (colSet.contains(board[j][i])) return false;
                    colSet.add(board[j][i]);
                }
            }
        }

        // Check 3x3 sub-boxes
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                
                Set<Character> boxSet = new HashSet<>();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char val = board[boxRow + i][boxCol + j];
                        
                        if (val != '.') {
                            if (boxSet.contains(val)) return false;
                            boxSet.add(val);
                        }
                    }
                }
            }
        }

        return true;
    }
}