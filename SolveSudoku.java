class Solution {
    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][] box = new boolean[9][10];

    public boolean solve(char[][] board, int r, int c) {
        if (r == 9) return true; // solved

        int nextR = (c == 8) ? r + 1 : r;
        int nextC = (c + 1) % 9;

        if (board[r][c] != '.') {
            return solve(board, nextR, nextC);
        }

        int b = (r / 3) * 3 + (c / 3);
        for (int num = 1; num <= 9; num++) {
            if (!row[r][num] && !col[c][num] && !box[b][num]) {
                // place number
                board[r][c] = (char)(num + '0');
                row[r][num] = col[c][num] = box[b][num] = true;

                if (solve(board, nextR, nextC)) return true;

                // backtrack
                board[r][c] = '.';
                row[r][num] = col[c][num] = box[b][num] = false;
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        // initialize trackers
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int b = (i / 3) * 3 + (j / 3);
                    row[i][num] = col[j][num] = box[b][num] = true;
                }
            }
        }
        solve(board, 0, 0);
    }
}
