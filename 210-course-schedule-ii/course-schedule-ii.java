class Solution {
    public int[] findOrder(int n, int[][] pre) {
        List<List<Integer>> g=new ArrayList<>();
        int[] indeg=new int[n];

        for(int i=0;i<n;i++) g.add(new ArrayList<>());
        for(int[] p:pre){
            g.get(p[1]).add(p[0]);
            indeg[p[0]]++;
        }

        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++)
            if(indeg[i]==0) q.offer(i);

        int[] res=new int[n];
        int idx=0;

        while(!q.isEmpty()){
            int cur=q.poll();
            res[idx++]=cur;
            for(int nei:g.get(cur)){
                if(--indeg[nei]==0) q.offer(nei);
            }
        }
        return idx==n?res:new int[0];
    }
}