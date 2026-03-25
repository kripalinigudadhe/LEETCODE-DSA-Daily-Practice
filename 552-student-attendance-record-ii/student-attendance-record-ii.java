class Solution {
    public int checkRecord(int n) {
        int mod = 1000000007;
        long[][] dp = new long[2][3];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            long[][] newDp = new long[2][3];

            for (int a = 0; a < 2; a++) {
                for (int l = 0; l < 3; l++) {
                    long val = dp[a][l];
                    if (val == 0) continue;

                    newDp[a][0] = (newDp[a][0] + val) % mod;

                    if (a == 0)
                        newDp[1][0] = (newDp[1][0] + val) % mod;

                    if (l < 2)
                        newDp[a][l + 1] = (newDp[a][l + 1] + val) % mod;
                }
            }
            dp = newDp;
        }

        long sum = 0;
        for (long[] row : dp)
            for (long v : row)
                sum = (sum + v) % mod;

        return (int) sum;
    }
}