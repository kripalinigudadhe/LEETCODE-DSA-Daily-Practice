class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        // Step 1: Validate diagonal
        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        char[] res = new char[n];
        Arrays.fill(res, '#');

        char ch = 'a';

        // Step 2: Assign characters
        for (int i = 0; i < n; i++) {
            if (res[i] == '#') {
                if (ch > 'z') return "";
                assign(i, ch, res, lcp);
                ch++;
            }
        }

        // Step 3: Validate LCP
        if (!validate(res, lcp)) return "";

        return new String(res);
    }

    private void assign(int idx, char ch, char[] res, int[][] lcp) {
        int n = res.length;

        for (int j = idx; j < n; j++) {
            if (lcp[idx][j] > 0) {
                if (res[j] == '#' || res[j] == ch) {
                    res[j] = ch;
                }
            }
        }
    }

    private boolean validate(char[] res, int[][] lcp) {
        int n = res.length;

        int[][] dp = new int[n + 1][n + 1];

        // Build LCP from string
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (res[i] == res[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                }
            }
        }

        // Compare with given lcp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] != lcp[i][j]) return false;
            }
        }

        return true;
    }
}