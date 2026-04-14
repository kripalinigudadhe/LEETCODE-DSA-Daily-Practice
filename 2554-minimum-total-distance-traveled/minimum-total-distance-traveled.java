import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int n = robot.size();
        int m = factory.length;

        Long[][] dp = new Long[n][m];

        return solve(0, 0, robot, factory, dp);
    }

    private long solve(int i, int j, List<Integer> robot, int[][] factory, Long[][] dp) {
        // All robots assigned
        if (i == robot.size()) return 0;

        // No factories left
        if (j == factory.length) return Long.MAX_VALUE;

        if (dp[i][j] != null) return dp[i][j];

        // Option 1: Skip this factory
        long res = solve(i, j + 1, robot, factory, dp);

        // Option 2: Assign robots to this factory
        long cost = 0;
        int pos = factory[j][0];
        int limit = factory[j][1];

        for (int k = 0; k < limit && i + k < robot.size(); k++) {
            cost += Math.abs(robot.get(i + k) - pos);

            long next = solve(i + k + 1, j + 1, robot, factory, dp);
            if (next != Long.MAX_VALUE) {
                res = Math.min(res, cost + next);
            }
        }

        return dp[i][j] = res;
    }
}