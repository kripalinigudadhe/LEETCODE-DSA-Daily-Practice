class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        for (int[] p : points) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int[] q : points) {
                int dx = p[0] - q[0];
                int dy = p[1] - q[1];
                int dist = dx * dx + dy * dy;
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }
            for (int val : map.values())
                res += val * (val - 1);
        }
        return res;
    }
}