class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);

        List<String> res = new ArrayList<>();

        if (s.isEmpty()) {
            res.add("");
            return res;
        }

        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> sub = dfs(s.substring(word.length()), dict, memo);
                for (String str : sub) {
                    res.add(word + (str.isEmpty() ? "" : " " + str));
                }
            }
        }

        memo.put(s, res);
        return res;
    }
}