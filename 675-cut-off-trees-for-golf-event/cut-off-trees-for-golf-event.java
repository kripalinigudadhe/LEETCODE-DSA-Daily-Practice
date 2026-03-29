import java.util.*;

class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        Collections.sort(trees, (a, b) -> a[0] - b[0]);

        int sx = 0, sy = 0, steps = 0;

        for (int[] t : trees) {
            int d = bfs(forest, sx, sy, t[1], t[2]);
            if (d == -1) return -1;
            steps += d;
            sx = t[1];
            sy = t[2];
        }

        return steps;
    }

    private int bfs(List<List<Integer>> forest, int sx, int sy, int tx, int ty) {
        int m = forest.size(), n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == tx && cur[1] == ty) return cur[2];

            for (int[] d : dirs) {
                int nx = cur[0] + d[0], ny = cur[1] + d[1];
                if (nx>=0 && ny>=0 && nx<m && ny<n &&
                    !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, cur[2]+1});
                }
            }
        }
        return -1;
    }
}