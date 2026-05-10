class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;

        // dp[i] = maximum jumps needed to reach index i
        int[] dp = new int[n];

        // initialize with -1 (unreachable)
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        dp[0] = 0; // starting index

        for (int i = 0; i < n; i++) {

            // if current index is unreachable, skip
            if (dp[i] == -1) continue;

            for (int j = i + 1; j < n; j++) {

                // check jump condition
                if (Math.abs(nums[j] - nums[i]) <= target) {

                    // maximize jumps
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}