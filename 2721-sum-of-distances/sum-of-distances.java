import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];

        // Step 1: Map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Step 2: Process each group
        for (List<Integer> indices : map.values()) {
            int size = indices.size();
            long[] prefix = new long[size];

            // Build prefix sum of indices
            prefix[0] = indices.get(0);
            for (int i = 1; i < size; i++) {
                prefix[i] = prefix[i - 1] + indices.get(i);
            }

            for (int i = 0; i < size; i++) {
                int idx = indices.get(i);

                // Left side contribution
                long left = (long) idx * i - (i > 0 ? prefix[i - 1] : 0);

                // Right side contribution
                long right = (prefix[size - 1] - prefix[i]) - (long) idx * (size - i - 1);

                result[idx] = left + right;
            }
        }

        return result;
    }
}