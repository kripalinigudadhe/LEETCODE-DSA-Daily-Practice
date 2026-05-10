class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunks = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);

            // If maximum till now equals current index,
            // we can form a chunk here
            if (max == i) {
                chunks++;
            }
        }

        return chunks;
    }
}