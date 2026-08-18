class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[51];

        // Check every subarray of size k
        for (int i = 0; i <= n - k; i++) {
            boolean[] present = new boolean[51];

            // Find distinct elements in the current subarray
            for (int j = i; j < i + k; j++) {
                present[nums[j]] = true;
            }

            // Count how many subarrays contain each number
            for (int x = 0; x <= 50; x++) {
                if (present[x]) {
                    count[x]++;
                }
            }
        }

        // Find the largest number appearing in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}