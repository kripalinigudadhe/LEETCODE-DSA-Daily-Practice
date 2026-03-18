class Solution {
    public int divide(int dividend, int divisor) {
        
        // Edge case: overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Convert to long to avoid overflow
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int result = 0;

        while (dvd >= dvs) {
            long temp = dvs, multiple = 1;

            // Double the divisor using left shift
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            dvd -= temp;
            result += multiple;
        }

        // Handle sign
        if ((dividend < 0) ^ (divisor < 0)) {
            return -result;
        }

        return result;
    }
}