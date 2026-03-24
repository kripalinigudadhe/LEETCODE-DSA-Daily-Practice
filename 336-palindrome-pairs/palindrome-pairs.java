import java.util.*;

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            for (int j = 0; j <= word.length(); j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);

                if (isPalindrome(left)) {
                    String rev = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i) {
                        res.add(Arrays.asList(map.get(rev), i));
                    }
                }

                if (right.length() > 0 && isPalindrome(right)) {
                    String rev = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i) {
                        res.add(Arrays.asList(i, map.get(rev)));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}