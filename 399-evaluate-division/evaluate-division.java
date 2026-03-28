class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, values[i]);
            graph.get(b).put(a, 1 / values[i]);
        }

        double[] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            res[i] = dfs(graph, queries.get(i).get(0),
                              queries.get(i).get(1),
                              new HashSet<>(), 1.0);
        }
        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph,
                       String src, String dest,
                       Set<String> visited, double val) {

        if (!graph.containsKey(src)) return -1.0;
        if (src.equals(dest)) return val;

        visited.add(src);

        for (String nei : graph.get(src).keySet()) {
            if (!visited.contains(nei)) {
                double res = dfs(graph, nei, dest, visited,
                                 val * graph.get(src).get(nei));
                if (res != -1.0) return res;
            }
        }
        return -1.0;
    }
}