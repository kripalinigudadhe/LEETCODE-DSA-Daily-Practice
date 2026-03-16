import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Size 0 rhombus
                set.add(grid[i][j]);

                // Try all possible sizes
                for (int size = 1; size < 50; size++) {

                    if (i - size < 0 || i + size >= m || j - size < 0 || j + size >= n) {
                        break;
                    }

                    int sum = 0;

                    // Traverse 4 sides
                    int x = i - size, y = j;

                    // Top → Right
                    for (int k = 0; k < size; k++) {
                        sum += grid[x + k][y + k];
                    }

                    // Right → Bottom
                    for (int k = 0; k < size; k++) {
                        sum += grid[i + k][j + size - k];
                    }

                    // Bottom → Left
                    for (int k = 0; k < size; k++) {
                        sum += grid[i + size - k][j - k];
                    }

                    // Left → Top
                    for (int k = 0; k < size; k++) {
                        sum += grid[i - k][j - size + k];
                    }

                    set.add(sum);
                }
            }
        }

        // Get top 3 distinct values
        int size = Math.min(3, set.size());
        int[] result = new int[size];

        int idx = 0;
        for (int val : set) {
            if (idx == size) break;
            result[idx++] = val;
        }

        return result;
    }
}