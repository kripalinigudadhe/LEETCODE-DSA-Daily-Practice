import java.util.*;

class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digit = new ArrayList<>();

        // Store only non-zero digits
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                pos.add(i);
                digit.add(s.charAt(i) - '0');
            }
        }

        int k = digit.size();

        long[] prefixSum = new long[k + 1];
        long[] prefixHash = new long[k + 1];
        long[] pow10 = new long[k + 1];

        pow10[0] = 1;

        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < k; i++) {
            prefixSum[i + 1] = prefixSum[i] + digit.get(i);
            prefixHash[i + 1] =
                    (prefixHash[i] * 10 + digit.get(i)) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int L = queries[i][0];
            int R = queries[i][1];

            int left = lowerBound(pos, L);
            int right = upperBound(pos, R) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            long sum =
                    prefixSum[right + 1] - prefixSum[left];

            int len = right - left + 1;

            long number =
                    prefixHash[right + 1]
                            - (prefixHash[left] * pow10[len]) % MOD;

            number = (number % MOD + MOD) % MOD;

            ans[i] = (int) ((number * sum) % MOD);
        }

        return ans;
    }

    // First index >= target
    private int lowerBound(ArrayList<Integer> arr, int target) {

        int l = 0;
        int r = arr.size();

        while (l < r) {

            int mid = (l + r) / 2;

            if (arr.get(mid) >= target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }

    // First index > target
    private int upperBound(ArrayList<Integer> arr, int target) {

        int l = 0;
        int r = arr.size();

        while (l < r) {

            int mid = (l + r) / 2;

            if (arr.get(mid) > target)
                r = mid;
            else
                l = mid + 1;
        }

        return l;
    }
}