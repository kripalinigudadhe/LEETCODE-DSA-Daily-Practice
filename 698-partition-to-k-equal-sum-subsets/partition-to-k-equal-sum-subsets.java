class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;

        if (sum % k != 0) return false;

        boolean[] used = new boolean[nums.length];
        return backtrack(nums, used, k, 0, 0, sum / k);
    }

    private boolean backtrack(int[] nums, boolean[] used, int k, int start, int curr, int target) {
        if (k == 1) return true;
        if (curr == target)
            return backtrack(nums, used, k - 1, 0, 0, target);

        for (int i = start; i < nums.length; i++) {
            if (used[i] || curr + nums[i] > target) continue;

            used[i] = true;
            if (backtrack(nums, used, k, i + 1, curr + nums[i], target))
                return true;
            used[i] = false;
        }
        return false;
    }
}