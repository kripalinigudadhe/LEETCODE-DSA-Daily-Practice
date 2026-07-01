import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

        // Multi-source BFS from all thieves
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n
                        && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Max-Heap: {safeness, row, col}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0], a[0]));

        boolean[][] visited = new boolean[n][n];

        pq.offer(new int[]{dist[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int safe = cur[0];
            int r = cur[1];
            int c = cur[2];

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (r == n - 1 && c == n - 1) {
                return safe;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n
                        && !visited[nr][nc]) {

                    int newSafe = Math.min(safe, dist[nr][nc]);
                    pq.offer(new int[]{newSafe, nr, nc});
                }
            }
        }

        return 0;
    }
}