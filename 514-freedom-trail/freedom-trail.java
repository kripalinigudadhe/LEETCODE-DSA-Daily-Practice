import java.util.*;

class Solution {
    Map<String, Integer> memo = new HashMap<>();
    Map<Character, List<Integer>> map = new HashMap<>();

    public int findRotateSteps(String ring, String key) {
        for (int i = 0; i < ring.length(); i++) {
            map.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }
        return dfs(ring, key, 0, 0);
    }

    private int dfs(String ring, String key, int pos, int idx) {
        if (idx == key.length()) return 0;

        String state = pos + "," + idx;
        if (memo.containsKey(state)) return memo.get(state);

        int res = Integer.MAX_VALUE;

        for (int next : map.get(key.charAt(idx))) {
            int diff = Math.abs(next - pos);
            int step = Math.min(diff, ring.length() - diff);

            res = Math.min(res, step + 1 + dfs(ring, key, next, idx + 1));
        }

        memo.put(state, res);
        return res;
    }
}