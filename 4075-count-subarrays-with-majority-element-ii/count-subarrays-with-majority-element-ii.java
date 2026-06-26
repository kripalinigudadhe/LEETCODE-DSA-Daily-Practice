class Solution {

    static class Fenwick {
        long[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void update(int idx, long val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Prefix sums range from [-n, n]
        int offset = n + 1;
        int size = 2 * n + 5;

        Fenwick bit = new Fenwick(size);

        long ans = 0;
        int prefix = 0;

        // Insert prefix sum 0
        bit.update(offset, 1);

        for (int x : nums) {
            prefix += (x == target) ? 1 : -1;

            int idx = prefix + offset;

            // Count previous prefix sums strictly smaller than current
            ans += bit.query(idx - 1);

            bit.update(idx, 1);
        }

        return ans;
    }
}