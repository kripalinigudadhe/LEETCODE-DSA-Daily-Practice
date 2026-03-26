class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num; // XOR of all numbers
        }

        // Get rightmost set bit
        int diffBit = xor & -xor;

        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                num1 ^= num; // group with 0 at diffBit
            } else {
                num2 ^= num; // group with 1 at diffBit
            }
        }

        return new int[]{num1, num2};
    }
}