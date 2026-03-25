class Solution {
    public boolean canFinish(int n, int[][] pre) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<n;i++) graph.add(new ArrayList<>());

        for(int[] p:pre) graph.get(p[1]).add(p[0]);

        int[] visited = new int[n]; // 0=unvisited,1=visiting,2=done

        for(int i=0;i<n;i++){
            if(!dfs(graph, visited, i)) return false;
        }
        return true;
    }

    boolean dfs(List<List<Integer>> g,int[] v,int i){
        if(v[i]==1) return false;
        if(v[i]==2) return true;

        v[i]=1;
        for(int nei:g.get(i)){
            if(!dfs(g,v,nei)) return false;
        }
        v[i]=2;
        return true;
    }
}