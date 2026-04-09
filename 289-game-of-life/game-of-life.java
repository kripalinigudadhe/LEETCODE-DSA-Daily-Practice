class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int live = 0;

                for (int d = 0; d < 8; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];

                    if (ni >= 0 && nj >= 0 && ni < m && nj < n) {
                        if (Math.abs(board[ni][nj]) == 1) {
                            live++;
                        }
                    }
                }

                if (board[i][j] == 1) {
                    if (live < 2 || live > 3) {
                        board[i][j] = -1; // live → dead
                    }
                } else {
                    if (live == 3) {
                        board[i][j] = 2; // dead → live
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}