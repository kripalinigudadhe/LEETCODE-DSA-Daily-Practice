class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long mn = Long.MAX_VALUE;
        long mx = Long.MIN_VALUE;

        for (int num : nums) {
            mn = Math.min(mn, num);
            mx = Math.max(mx, num);
        }

        return (mx - mn) * (long) k;
    }
}