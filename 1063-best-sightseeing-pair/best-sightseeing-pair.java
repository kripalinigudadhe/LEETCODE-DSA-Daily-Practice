class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int best = values[0];
        int res = 0;

        for (int j = 1; j < values.length; j++) {
            res = Math.max(res, best + values[j] - j);
            best = Math.max(best, values[j] + j);
        }

        return res;
    }
}