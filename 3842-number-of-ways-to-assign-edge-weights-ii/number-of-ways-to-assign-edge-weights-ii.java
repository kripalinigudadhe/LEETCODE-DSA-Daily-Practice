import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] up;
    private int[] depth;
    private int LOG;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        bfs(graph, n);

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int maxDepth = n;
        long[] pow2 = new long[maxDepth + 1];
        pow2[0] = 1;
        for (int i = 1; i <= maxDepth; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            int lca = lca(u, v);
            int len = depth[u] + depth[v] - 2 * depth[lca];

            if (len == 0) {
                ans[i] = 0;
            } else {
                ans[i] = (int) pow2[len - 1];
            }
        }

        return ans;
    }

    private void bfs(List<Integer>[] graph, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];

        q.offer(1);
        vis[1] = true;
        up[1][0] = 0;
        depth[1] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : graph[node]) {
                if (!vis[nei]) {
                    vis[nei] = true;
                    depth[nei] = depth[node] + 1;
                    up[nei][0] = node;
                    q.offer(nei);
                }
            }
        }
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int j = LOG - 1; j >= 0; j--) {
            if (((diff >> j) & 1) == 1) {
                a = up[a][j];
            }
        }

        if (a == b) return a;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[a][j] != up[b][j]) {
                a = up[a][j];
                b = up[b][j];
            }
        }

        return up[a][0];
    }
}