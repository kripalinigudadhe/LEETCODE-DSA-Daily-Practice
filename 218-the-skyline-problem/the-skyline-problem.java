class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(0);

        int prev = 0;

        for (int[] e : events) {
            if (e[1] < 0) pq.add(-e[1]);
            else pq.remove(e[1]);

            int cur = pq.peek();
            if (cur != prev) {
                res.add(Arrays.asList(e[0], cur));
                prev = cur;
            }
        }
        return res;
    }
}