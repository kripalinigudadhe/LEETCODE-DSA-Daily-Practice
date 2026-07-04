import java.util.*;

class Solution {
    static class Pair {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    public int minScore(int n, int[][] roads) {
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph.get(u).add(new Pair(v, d));
            graph.get(v).add(new Pair(u, d));
        }

        boolean[] visited = new boolean[n + 1];
        return dfs(1, graph, visited);
    }

    private int dfs(int node, List<List<Pair>> graph, boolean[] visited) {
        visited[node] = true;
        int ans = Integer.MAX_VALUE;

        for (Pair nei : graph.get(node)) {
            ans = Math.min(ans, nei.dist);

            if (!visited[nei.node]) {
                ans = Math.min(ans, dfs(nei.node, graph, visited));
            }
        }

        return ans;
    }
}