class Solution {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int idx = 0;

        // Step 1: Fill increasing sequence
        for (int i = 1; i <= n - k - 1; i++) {
            res[idx++] = i;
        }

        // Step 2: Create k distinct differences
        int left = n - k, right = n;
        while (left <= right) {
            res[idx++] = left++;
            if (left <= right) {
                res[idx++] = right--;
            }
        }

        return res;
    }
}