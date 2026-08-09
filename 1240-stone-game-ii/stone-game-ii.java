class Solution {

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = total stones from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M] = maximum stones current player can get
        // starting from index i with current M
        int[][] dp = new int[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return dfs(0, 1, piles, suffix, dp);
    }

    private int dfs(int i, int M, int[] piles, int[] suffix, int[][] dp) {

        int n = piles.length;

        // All remaining stones can be taken
        if (i >= n) {
            return 0;
        }

        if (2 * M >= n - i) {
            return suffix[i];
        }

        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int best = 0;

        /*
         * Try taking X piles.
         * 1 <= X <= 2*M
         */
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            /*
             * Current player takes X piles.
             *
             * Remaining stones = suffix[i + X]
             *
             * Opponent can get:
             * dfs(i + X, max(M, X))
             *
             * Therefore current player gets:
             *
             * remaining stones - opponent's best
             */
            int opponent = dfs(
                i + X,
                Math.max(M, X),
                piles,
                suffix,
                dp
            );

            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}