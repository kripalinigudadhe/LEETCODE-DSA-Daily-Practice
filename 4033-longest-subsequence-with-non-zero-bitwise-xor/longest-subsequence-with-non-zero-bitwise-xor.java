class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        // XOR of entire array is non-zero
        if (xor != 0) {
            return n;
        }

        // XOR is zero, but we have a non-zero element
        if (hasNonZero) {
            return n - 1;
        }

        // All elements are zero
        return 0;
    }
}