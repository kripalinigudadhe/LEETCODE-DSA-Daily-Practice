class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int[][][] dp = new int[m][n][k + 1];

        // Initialize with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;

                int val = grid[i][j];
                int score = val;
                int cost = (val == 0) ? 0 : 1;

                for (int c = 0; c <= k; c++) {
                    int best = -1;

                    // From top
                    if (i > 0 && c >= cost && dp[i - 1][j][c - cost] != -1) {
                        best = Math.max(best, dp[i - 1][j][c - cost] + score);
                    }

                    // From left
                    if (j > 0 && c >= cost && dp[i][j - 1][c - cost] != -1) {
                        best = Math.max(best, dp[i][j - 1][c - cost] + score);
                    }

                    dp[i][j][c] = Math.max(dp[i][j][c], best);
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}