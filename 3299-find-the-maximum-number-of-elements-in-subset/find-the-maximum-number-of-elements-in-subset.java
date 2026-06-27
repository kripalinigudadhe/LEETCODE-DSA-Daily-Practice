import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Special handling for 1
        if (freq.containsKey(1L)) {
            int c = freq.get(1L);
            ans = Math.max(ans, (c % 2 == 1) ? c : c - 1);
        }

        for (long start : freq.keySet()) {
            if (start == 1L) continue;

            long cur = start;
            int len = 0;

            while (freq.getOrDefault(cur, 0) >= 2) {
                len += 2;
                cur = cur * cur;

                // Prevent unnecessary overflow growth
                if (cur > (long) 1e18) break;
            }

            if (freq.getOrDefault(cur, 0) >= 1) {
                len += 1;      // use cur as the center element
            } else {
                len -= 1;      // last pair cannot be fully utilized
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}