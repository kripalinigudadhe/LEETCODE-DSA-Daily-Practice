class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                boolean ok = true;
                for (String s : strs) {
                    if (s.charAt(j) > s.charAt(i)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int v : dp) max = Math.max(max, v);
        return n - max;
    }
}