class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;

        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;

        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], w);

            if (map.containsKey(id)) return true;
            if (map.containsKey(id - 1) &&
                Math.abs(nums[i] - map.get(id - 1)) < w) return true;
            if (map.containsKey(id + 1) &&
                Math.abs(nums[i] - map.get(id + 1)) < w) return true;

            map.put(id, (long) nums[i]);

            if (i >= k) map.remove(getId(nums[i - k], w));
        }
        return false;
    }

    private long getId(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }
}