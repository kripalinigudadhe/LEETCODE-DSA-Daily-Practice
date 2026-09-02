class Solution {
    public boolean uniformArray(int[] nums1) {
        
        int oddCount = 0;
        int evenCount = 0;

        for (int num : nums1) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        // If all numbers are even, keep them as they are.
        if (evenCount == nums1.length) {
            return true;
        }

        // If there is at least one odd number,
        // we can make all elements odd:
        // - Keep odd numbers unchanged.
        // - For every even number, subtract an odd number.
        //   even - odd = odd.
        if (oddCount > 0) {
            return true;
        }

        return false;
    }
}