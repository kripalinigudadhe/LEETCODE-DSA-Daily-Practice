class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        // Step 1: Calculate total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }

        // If total sum is odd, can't split equally
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

        // Step 2: Try horizontal cut
        long rowSum = 0;
        for (int i = 0; i < m - 1; i++) { // ensure non-empty second part
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == target) return true;
        }

        // Step 3: Compute column sums
        long[] colSum = new long[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colSum[j] += grid[i][j];
            }
        }

        // Step 4: Try vertical cut
        long currColSum = 0;
        for (int j = 0; j < n - 1; j++) { // ensure non-empty second part
            currColSum += colSum[j];
            if (currColSum == target) return true;
        }

        return false;
    }
}