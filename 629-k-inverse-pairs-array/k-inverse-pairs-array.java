class Solution {
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j <= k; j++) {
                sum = (sum + dp[i - 1][j]) % mod;
                if (j >= i) sum = (sum - dp[i - 1][j - i] + mod) % mod;
                dp[i][j] = sum;
            }
        }

        return dp[n][k];
    }
}