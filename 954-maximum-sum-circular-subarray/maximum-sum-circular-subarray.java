class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0, maxSum = nums[0], curMax = 0;
        int minSum = nums[0], curMin = 0;

        for (int n : nums) {
            curMax = Math.max(curMax + n, n);
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(curMin + n, n);
            minSum = Math.min(minSum, curMin);

            total += n;
        }

        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}