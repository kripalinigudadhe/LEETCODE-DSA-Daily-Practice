class Solution {

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d, dp));
        }

        return ans;
    }

    private int dfs(int i, int[] arr, int d, int[] dp) {

        // If already calculated
        if (dp[i] != 0) {
            return dp[i];
        }

        int max = 1;

        // Check right side
        for (int j = i + 1; j <= Math.min(i + d, arr.length - 1); j++) {

            // Cannot jump further if higher/equal element found
            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(j, arr, d, dp));
        }

        // Check left side
        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {

            // Cannot jump further if higher/equal element found
            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(j, arr, d, dp));
        }

        dp[i] = max;
        return max;
    }
}