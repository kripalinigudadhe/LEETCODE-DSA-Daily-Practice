class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        int aliceTotal = 0;
        int bobTotal = 0;

        // Calculate Alice's total
        for (int i = 0; i < aliceSizes.length; i++) {
            aliceTotal = aliceTotal + aliceSizes[i];
        }

        // Calculate Bob's total
        for (int i = 0; i < bobSizes.length; i++) {
            bobTotal = bobTotal + bobSizes[i];
        }

        // Difference that needs to be exchanged
        int diff = (aliceTotal - bobTotal) / 2;

        // Check every pair
        for (int i = 0; i < aliceSizes.length; i++) {

            for (int j = 0; j < bobSizes.length; j++) {

                if (aliceSizes[i] - bobSizes[j] == diff) {
                    return new int[]{aliceSizes[i], bobSizes[j]};
                }
            }
        }

        return new int[]{};
    }
}