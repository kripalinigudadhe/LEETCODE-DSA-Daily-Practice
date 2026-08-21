import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k;

        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {
        return inclusionExclusion(x, coins, 0, 1);
    }

    private long inclusionExclusion(long x, int[] coins,
                                    int index, long lcm) {

        long result = 0;

        for (int i = index; i < coins.length; i++) {

            long newLcm = lcm(lcm, coins[i]);

            if (newLcm > x) {
                continue;
            }

            // Multiples of newLcm <= x
            long cnt = x / newLcm;

            // Add this subset
            result += cnt;

            // Subtract intersections of larger subsets
            result -= inclusionExclusion(
                    x, coins, i + 1, newLcm
            );
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}