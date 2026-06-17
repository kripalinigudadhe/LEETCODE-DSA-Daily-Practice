class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long LIMIT = (long) 1e15 + 1;

        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            long cur = len[i];

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = Math.min(LIMIT, cur + 1);
            } else if (c == '*') {
                len[i + 1] = Math.max(0, cur - 1);
            } else if (c == '#') {
                len[i + 1] = Math.min(LIMIT, cur * 2);
            } else { // '%'
                len[i + 1] = cur;
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prevLen = len[i];

            if (c >= 'a' && c <= 'z') {
                if (k == prevLen) {
                    return c;
                }
            } else if (c == '*') {
                // After deletion, indices remain unchanged.
            } else if (c == '#') {
                if (k >= prevLen) {
                    k -= prevLen;
                }
            } else { // '%'
                k = prevLen - 1 - k;
            }
        }

        return '.';
    }
}