class Solution {

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int bestIdx = -1;
        int bestLen = Integer.MAX_VALUE;
    }

    private void update(TrieNode node, int idx, int len) {
        if (len < node.bestLen ||
            (len == node.bestLen && idx < node.bestIdx)) {
            node.bestLen = len;
            node.bestIdx = idx;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        TrieNode root = new TrieNode();

        // Build Trie with reversed container words
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            int len = word.length();

            TrieNode curr = root;
            update(curr, i, len);

            for (int j = len - 1; j >= 0; j--) {
                int c = word.charAt(j) - 'a';

                if (curr.child[c] == null) {
                    curr.child[c] = new TrieNode();
                }

                curr = curr.child[c];
                update(curr, i, len);
            }
        }

        int[] ans = new int[wordsQuery.length];

        // Process queries
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];

            TrieNode curr = root;
            int answer = root.bestIdx;

            for (int j = query.length() - 1; j >= 0; j--) {
                int c = query.charAt(j) - 'a';

                if (curr.child[c] == null) {
                    break;
                }

                curr = curr.child[c];
                answer = curr.bestIdx;
            }

            ans[i] = answer;
        }

        return ans;
    }
}