class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];

        int idx = 0;

        // Elements less than pivot
        for (int num : nums) {
            if (num < pivot) {
                result[idx++] = num;
            }
        }

        // Elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                result[idx++] = num;
            }
        }

        // Elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                result[idx++] = num;
            }
        }

        return result;
    }
}