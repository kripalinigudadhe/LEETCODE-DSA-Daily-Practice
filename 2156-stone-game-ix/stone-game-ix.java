class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];

        // Count stones according to remainder modulo 3
        for (int stone : stones) {
            cnt[stone % 3]++;
        }

        // If there are no stones with remainder 1 or 2,
        // Alice cannot make a winning move.
        if (cnt[1] == 0 && cnt[2] == 0) {
            return false;
        }

        // If number of 0-modulo stones is even
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }

        // If number of 0-modulo stones is odd
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}