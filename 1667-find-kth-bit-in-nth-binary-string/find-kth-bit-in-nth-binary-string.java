class Solution {
    public char findKthBit(int n, int k) {
        // Base case
        if (n == 1) {
            return '0';
        }

        int length = (1 << n) - 1;  // 2^n - 1
        int mid = (length / 2) + 1;

        // If k is the middle element
        if (k == mid) {
            return '1';
        }

        // If k is in left half
        if (k < mid) {
            return findKthBit(n - 1, k);
        } 
        // If k is in right half
        else {
            int newK = length - k + 1;
            char bit = findKthBit(n - 1, newK);
            
            // Invert the bit
            return bit == '0' ? '1' : '0';
        }
    }
}