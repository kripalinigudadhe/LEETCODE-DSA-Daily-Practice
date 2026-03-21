class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][] dp = new double[n][n];
        dp[row][column] = 1;

        int[][] moves = {
            {2,1},{1,2},{-1,2},{-2,1},
            {-2,-1},{-1,-2},{1,-2},{2,-1}
        };

        for (int step = 0; step < k; step++) {
            double[][] next = new double[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dp[i][j] > 0) {
                        for (int[] m : moves) {
                            int ni = i + m[0];
                            int nj = j + m[1];
                            if (ni >= 0 && nj >= 0 && ni < n && nj < n) {
                                next[ni][nj] += dp[i][j] / 8.0;
                            }
                        }
                    }
                }
            }
            dp = next;
        }

        double sum = 0;
        for (double[] r : dp)
            for (double v : r) sum += v;

        return sum;
    }
}