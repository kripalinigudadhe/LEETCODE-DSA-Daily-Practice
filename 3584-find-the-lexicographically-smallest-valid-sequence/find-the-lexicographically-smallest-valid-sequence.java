class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] ans = new int[m];

        /*
         * last[j] = position in word1 where word2[j]
         * can be matched while matching word2 from right to left.
         */
        int[] last = new int[m];

        for (int i = 0; i < m; i++) {
            last[i] = -1;
        }

        int i = n - 1;
        int j = m - 1;

        // Match word2 from right to left
        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }

            i--;
        }

        /*
         * Greedily construct the lexicographically
         * smallest sequence.
         */
        i = 0;
        j = 0;

        // We are allowed to modify one character
        boolean canChange = true;

        while (i < n && j < m) {

            // Case 1: Characters already match
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            // Case 2: Characters don't match
            else if (canChange) {

                /*
                 * We can change word1[i] to word2[j].
                 *
                 * But we must make sure that the remaining
                 * word2 characters can still be matched.
                 */
                if (j == m - 1 || i < last[j + 1]) {

                    ans[j] = i;
                    j++;

                    // Use our one allowed modification
                    canChange = false;
                }
            }

            i++;
        }

        /*
         * If we couldn't match all characters,
         * no valid sequence exists.
         */
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}