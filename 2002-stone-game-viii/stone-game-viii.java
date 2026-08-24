class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Prefix sums
        int[] prefix = new int[n];
        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // If Alice takes all stones
        int best = prefix[n - 1];

        // Try all possible split points
        for (int i = n - 2; i >= 1; i--) {
            best = Math.max(best, prefix[i] - best);
        }

        return best;
    }
}