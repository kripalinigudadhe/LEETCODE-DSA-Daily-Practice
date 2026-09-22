class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][1001];
        int ans = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                int index = diff + 500;

                if (dp[j][index] == 0) {
                    dp[i][index] = 2;
                } else {
                    dp[i][index] = dp[j][index] + 1;
                }

                ans = Math.max(ans, dp[i][index]);
            }
        }

        return ans;
    }
}