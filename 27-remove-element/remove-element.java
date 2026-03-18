class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0; // pointer for placing valid elements

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i]; // keep the element
                k++;
            }
        }

        return k;
    }
}