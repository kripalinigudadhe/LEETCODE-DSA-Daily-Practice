class Solution {

    static class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int maxLen;
        int len;

        Node() {}

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            maxLen = 1;
            len = 1;
        }
    }

    private Node[] tree;
    private char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {

        s = str.toCharArray();

        int n = s.length;
        int k = queryIndices.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Update the character
            s[index] = ch;

            update(1, 0, n - 1, index, ch);

            // Root contains the answer for the whole string
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(s[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one position
    private void update(int node, int left, int right, int index, char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    private Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Prefix
        res.prefix = a.prefix;

        if (a.prefix == a.len && a.rightChar == b.leftChar) {
            res.prefix = a.len + b.prefix;
        }

        // Suffix
        res.suffix = b.suffix;

        if (b.suffix == b.len && a.rightChar == b.leftChar) {
            res.suffix = b.len + a.suffix;
        }

        // Maximum inside either segment
        res.maxLen = Math.max(a.maxLen, b.maxLen);

        // Join suffix of left + prefix of right
        if (a.rightChar == b.leftChar) {
            res.maxLen = Math.max(
                res.maxLen,
                a.suffix + b.prefix
            );
        }

        return res;
    }
}