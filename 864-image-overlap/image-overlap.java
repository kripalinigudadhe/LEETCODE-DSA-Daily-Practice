class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        Map<String, Integer> map = new HashMap<>();
        int n = A.length, res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (B[x][y] == 1) {
                                String key = (i - x) + "," + (j - y);
                                int val = map.getOrDefault(key, 0) + 1;
                                map.put(key, val);
                                res = Math.max(res, val);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}