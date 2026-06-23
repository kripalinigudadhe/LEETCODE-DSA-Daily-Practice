class Solution {
    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2 initialization
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // previous value < v
            down[v] = m - v;    // previous value > v
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {
            long[] upNew = new long[m + 1];
            long[] downNew = new long[m + 1];

            long[] prefDown = new long[m + 1];
            long[] prefUp = new long[m + 1];

            for (int i = 1; i <= m; i++) {
                prefDown[i] = (prefDown[i - 1] + down[i]) % MOD;
                prefUp[i] = (prefUp[i - 1] + up[i]) % MOD;
            }

            for (int v = 1; v <= m; v++) {
                // Previous sign must be DOWN and previous value < v
                upNew[v] = prefDown[v - 1];

                // Previous sign must be UP and previous value > v
                downNew[v] = (prefUp[m] - prefUp[v] + MOD) % MOD;
            }

            up = upNew;
            down = downNew;
        }

        long ans = 0;

        if (n == 2) {
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
            return (int) ans;
        }

        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}