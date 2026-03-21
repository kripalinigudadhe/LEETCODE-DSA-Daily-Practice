class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;
        int[][] count = new int[n][26];

        for (int i = 0; i < n; i++)
            for (char c : stickers[i].toCharArray())
                count[i][c - 'a']++;

        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);

        return dfs(memo, count, target);
    }

    private int dfs(Map<String, Integer> memo, int[][] count, String target) {
        if (memo.containsKey(target)) return memo.get(target);

        int[] targetCount = new int[26];
        for (char c : target.toCharArray())
            targetCount[c - 'a']++;

        int res = Integer.MAX_VALUE;

        for (int[] sticker : count) {
            if (sticker[target.charAt(0) - 'a'] == 0) continue;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (targetCount[i] > 0) {
                    int remain = targetCount[i] - sticker[i];
                    for (int j = 0; j < Math.max(0, remain); j++)
                        sb.append((char) ('a' + i));
                }
            }

            String next = sb.toString();
            int temp = dfs(memo, count, next);

            if (temp != -1)
                res = Math.min(res, 1 + temp);
        }

        memo.put(target, res == Integer.MAX_VALUE ? -1 : res);
        return memo.get(target);
    }
}