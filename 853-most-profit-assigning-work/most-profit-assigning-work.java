class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] jobs = new int[difficulty.length][2];

        for (int i = 0; i < difficulty.length; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);

        int i = 0, best = 0, res = 0;

        for (int w : worker) {
            while (i < jobs.length && jobs[i][0] <= w) {
                best = Math.max(best, jobs[i][1]);
                i++;
            }
            res += best;
        }
        return res;
    }
}