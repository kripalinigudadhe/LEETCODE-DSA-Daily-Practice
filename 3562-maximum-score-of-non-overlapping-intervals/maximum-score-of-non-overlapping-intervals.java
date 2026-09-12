import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class Result {
        long score;
        List<Integer> list;

        Result(long score, List<Integer> list) {
            this.score = score;
            this.list = list;
        }
    }

    Interval[] arr;
    Result[][] dp;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();
        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by left endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.idx, b.idx);
        });

        dp = new Result[n + 1][5];

        Result ans = solve(0, 4);

        int[] result = new int[ans.list.size()];

        for (int i = 0; i < ans.list.size(); i++) {
            result[i] = ans.list.get(i);
        }

        return result;
    }

    Result solve(int i, int k) {

        if (i >= n || k == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Option 1: Skip current interval
        Result skip = solve(i + 1, k);

        // Option 2: Take current interval
        int next = findNext(i);

        Result nextResult = solve(next, k - 1);

        List<Integer> takeList = new ArrayList<>();
        takeList.add(arr[i].idx);
        takeList.addAll(nextResult.list);

        Collections.sort(takeList);

        Result take = new Result(
            arr[i].w + nextResult.score,
            takeList
        );

        // Select better result
        if (take.score > skip.score) {
            dp[i][k] = take;
        } 
        else if (take.score < skip.score) {
            dp[i][k] = skip;
        } 
        else {
            if (compare(take.list, skip.list) < 0) {
                dp[i][k] = take;
            } else {
                dp[i][k] = skip;
            }
        }

        return dp[i][k];
    }

    // Find first interval with left > current right
    int findNext(int i) {

        int target = arr[i].r;

        int low = i + 1;
        int high = n;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].l > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Lexicographical comparison
    int compare(List<Integer> a, List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}