class Solution {
    public int videoStitching(int[][] clips, int time) {
        int[] maxReach = new int[time + 1];

        for (int[] c : clips) {
            if (c[0] <= time)
                maxReach[c[0]] = Math.max(maxReach[c[0]], Math.min(time, c[1]));
        }

        int last = 0, pre = 0, res = 0;

        for (int i = 0; i < time; i++) {
            last = Math.max(last, maxReach[i]);
            if (i == last) return -1;
            if (i == pre) {
                res++;
                pre = last;
            }
        }

        return res;
    }
}