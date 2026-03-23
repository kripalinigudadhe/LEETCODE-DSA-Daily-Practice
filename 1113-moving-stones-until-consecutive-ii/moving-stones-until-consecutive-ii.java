import java.util.*;

class Solution {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;

        int max = Math.max(
            stones[n - 1] - stones[1] - n + 2,
            stones[n - 2] - stones[0] - n + 2
        );

        int min = n;
        int i = 0;

        for (int j = 0; j < n; j++) {
            while (stones[j] - stones[i] + 1 > n) i++;

            int already = j - i + 1;

            if (already == n - 1 && stones[j] - stones[i] == n - 2) {
                min = Math.min(min, 2);
            } else {
                min = Math.min(min, n - already);
            }
        }

        return new int[]{min, max};
    }
}