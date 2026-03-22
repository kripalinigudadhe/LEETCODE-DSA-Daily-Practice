import java.util.*;

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();

        for (String word : words) {
            if (word.length() == 0) continue;
            set.remove(word);
            if (canForm(word, set)) res.add(word);
            set.add(word);
        }
        return res;
    }

    private boolean canForm(String word, Set<String> set) {
        if (set.isEmpty()) return false;

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}