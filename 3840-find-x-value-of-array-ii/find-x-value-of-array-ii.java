class Solution {
    static class Node {
        int product;
        long[] count;

        Node(int k) {
            count = new long[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            nums[index] = value;
            update(1, 0, n - 1, index);

            Node res = query(1, 0, n - 1, start, n - 1);

            result[i] = (int) res.count[x];
        }

        return result;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = new Node(k);
            int value = nums[l] % k;

            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index) {
        if (l == r) {
            tree[node] = new Node(k);

            int value = nums[index] % k;

            tree[node].product = value;
            tree[node].count[value] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {
        Node res = new Node(k);

        res.product = (a.product * b.product) % k;

        for (int r = 0; r < k; r++) {
            res.count[r] += a.count[r];
        }

        for (int r = 0; r < k; r++) {
            int newRemainder = (a.product * r) % k;
            res.count[newRemainder] += b.count[r];
        }

        return res;
    }

    Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}