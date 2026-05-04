class Solution {
    public void rotate(int[][] m) {
        int n = m.length;

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = temp;
            }
        }

        // reverse rows
        for (int[] row : m) {
            int l = 0, r = n - 1;
            while (l < r) {
                int temp = row[l];
                row[l++] = row[r];
                row[r--] = temp;
            }
        }
    }
}