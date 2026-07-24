class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX_XOR = 2048;

        boolean[] one = new boolean[MAX_XOR];
        for (int num : nums) {
            one[num] = true;
        }

        boolean[] two = new boolean[MAX_XOR];
        for (int x = 0; x < MAX_XOR; x++) {
            if (one[x]) {
                for (int num : nums) {
                    two[x ^ num] = true;
                }
            }
        }

        boolean[] three = new boolean[MAX_XOR];
        for (int x = 0; x < MAX_XOR; x++) {
            if (two[x]) {
                for (int num : nums) {
                    three[x ^ num] = true;
                }
            }
        }

        int count = 0;
        for (boolean val : three) {
            if (val) count++;
        }

        return count;
    }
}