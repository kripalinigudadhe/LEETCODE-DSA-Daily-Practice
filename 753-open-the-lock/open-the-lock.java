class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains("0000")) return -1;

        Queue<String> q = new LinkedList<>();
        Set<String> vis = new HashSet<>();

        q.offer("0000");
        vis.add("0000");

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                String curr = q.poll();
                if (curr.equals(target)) return steps;

                for (String next : neighbors(curr)) {
                    if (!dead.contains(next) && vis.add(next)) {
                        q.offer(next);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private List<String> neighbors(String s) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            char[] arr = s.toCharArray();

            arr[i] = (char)((arr[i] - '0' + 1) % 10 + '0');
            res.add(new String(arr));

            arr = s.toCharArray();
            arr[i] = (char)((arr[i] - '0' + 9) % 10 + '0');
            res.add(new String(arr));
        }

        return res;
    }
}