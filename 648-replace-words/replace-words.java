import java.util.*;

class Solution {
    class Trie {
        Trie[] children = new Trie[26];
        String word;
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie root = new Trie();

        for (String d : dict) {
            Trie node = root;
            for (char c : d.toCharArray()) {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new Trie();
                node = node.children[c - 'a'];
            }
            node.word = d;
        }

        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        for (String w : words) {
            Trie node = root;
            String replace = null;

            for (char c : w.toCharArray()) {
                if (node.children[c - 'a'] == null) break;
                node = node.children[c - 'a'];
                if (node.word != null) {
                    replace = node.word;
                    break;
                }
            }

            res.append(replace != null ? replace : w).append(" ");
        }

        return res.toString().trim();
    }
}