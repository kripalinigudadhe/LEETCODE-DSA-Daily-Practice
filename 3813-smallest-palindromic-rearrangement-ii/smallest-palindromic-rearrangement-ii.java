import java.util.*;

class Solution {

    static final long LIMIT = 1_000_001L;

    long[][] comb;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        String mid = "";

        int[] half = new int[26];
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));

            half[i] = freq[i] / 2;
            halfLen += half[i];
        }

        buildComb(halfLen);

        if (countWays(half) < k)
            return "";

        StringBuilder left = new StringBuilder();

        while (halfLen > 0) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    halfLen--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String right = left.reverse().toString();
        left.reverse();

        return left.toString() + mid + right;
    }

    private void buildComb(int n) {

        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = Math.min(LIMIT,
                        comb[i - 1][j - 1] + comb[i - 1][j]);
            }
        }
    }

    private long countWays(int[] cnt) {

        int total = 0;
        long ans = 1;

        for (int x : cnt) {

            if (x == 0)
                continue;

            ans *= comb[total + x][x];

            if (ans > LIMIT)
                ans = LIMIT;

            total += x;
        }

        return ans;
    }
}