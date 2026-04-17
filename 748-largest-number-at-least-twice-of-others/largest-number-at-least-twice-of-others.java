class Solution {
    public int dominantIndex(int[] nums) {
        int max = -1, second = -1, idx = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                second = max;
                max = nums[i];
                idx = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }

        return max >= 2 * second ? idx : -1;
    }
}