class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0; // position to place next non-zero

        // Step 1: Move all non-zero elements forward
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        // Step 2: Fill remaining positions with 0
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}