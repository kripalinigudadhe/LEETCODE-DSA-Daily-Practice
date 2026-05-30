import java.util.*;

class Solution {

    class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }

        void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) >> 1;

            if (idx <= mid)
                update(node * 2, l, mid, idx, val);
            else
                update(node * 2 + 1, mid + 1, r, idx, val);

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int L, int R) {
            if (L > R) return 0;
            return query(1, 0, n - 1, L, R);
        }

        private int query(int node, int l, int r, int L, int R) {
            if (L <= l && r <= R) return tree[node];

            int mid = (l + r) >> 1;
            int ans = 0;

            if (L <= mid)
                ans = Math.max(ans,
                        query(node * 2, l, mid, L, R));

            if (R > mid)
                ans = Math.max(ans,
                        query(node * 2 + 1, mid + 1, r, L, R));

            return ans;
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int maxX = 0;

        for (int[] q : queries) {
            maxX = Math.max(maxX, q[1]);
        }

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(maxX + 1);

        boolean[] isObstacle = new boolean[maxX + 2];

        for (int[] q : queries) {
            if (q[0] == 1) {
                obstacles.add(q[1]);
                isObstacle[q[1]] = true;
            }
        }

        SegmentTree seg = new SegmentTree(maxX + 2);

        Integer prev = null;

        for (int pos : obstacles) {
            if (prev != null) {
                seg.update(pos, pos - prev);
            }
            prev = pos;
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {

            int[] q = queries[i];

            if (q[0] == 2) {

                int x = q[1];
                int sz = q[2];

                Integer left = obstacles.floor(x);

                int best = seg.query(0, x);

                int tailGap = x - left;

                best = Math.max(best, tailGap);

                ans.add(best >= sz);

            } else {

                int x = q[1];

                Integer lower = obstacles.lower(x);
                Integer higher = obstacles.higher(x);

                obstacles.remove(x);

                seg.update(higher, higher - lower);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}