import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store {value, original index}
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];

        int i = 0;

        while (i < n) {
            int j = i;

            // Find a group where adjacent values differ by <= limit
            while (j + 1 < n && arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            // Collect original indices
            List<Integer> indices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                indices.add(arr[k][1]);
            }

            // Sort indices so smallest values can be placed at smallest positions
            Collections.sort(indices);

            // Values are already sorted in arr[i...j]
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = arr[i + k][0];
            }

            i = j + 1;
        }

        return result;
    }
}