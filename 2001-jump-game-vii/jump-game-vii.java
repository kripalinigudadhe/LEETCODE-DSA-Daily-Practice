class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();

        // visited/reachable positions
        boolean[] reachable = new boolean[n];
        reachable[0] = true;

        int farthest = 0;

        for (int i = 0; i < n; i++) {

            // if current index is not reachable, skip
            if (!reachable[i]) {
                continue;
            }

            // start and end range for next jumps
            int start = Math.max(i + minJump, farthest + 1);
            int end = Math.min(i + maxJump, n - 1);

            // mark all valid positions as reachable
            for (int j = start; j <= end; j++) {
                if (s.charAt(j) == '0') {
                    reachable[j] = true;
                }
            }

            // update farthest processed index
            farthest = end;

            // if last index reachable
            if (reachable[n - 1]) {
                return true;
            }
        }

        return reachable[n - 1];
    }
}