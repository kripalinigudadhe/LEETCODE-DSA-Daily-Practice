class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        int[][] px = new int[m + 1][n + 1]; // prefix count of X
        int[][] py = new int[m + 1][n + 1]; // prefix count of Y

        // Build prefix sums
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                px[i][j] = px[i-1][j] + px[i][j-1] - px[i-1][j-1];
                py[i][j] = py[i-1][j] + py[i][j-1] - py[i-1][j-1];

                if (grid[i-1][j-1] == 'X') px[i][j]++;
                if (grid[i-1][j-1] == 'Y') py[i][j]++;
            }
        }

        int result = 0;

        // Check all submatrices from (0,0) to (i,j)
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int countX = px[i][j];
                int countY = py[i][j];

                if (countX == countY && countX > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}