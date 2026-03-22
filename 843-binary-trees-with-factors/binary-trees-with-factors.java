import java.util.*;

class Solution {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        long mod = 1_000_000_007;
        Map<Integer, Long> dp = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            long ways = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (dp.containsKey(right)) {
                        ways += dp.get(arr[j]) * dp.get(right);
                    }
                }
            }

            dp.put(arr[i], ways % mod);
        }

        long res = 0;
        for (long v : dp.values()) res += v;

        return (int)(res % mod);
    }
}