class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int ones = 0;

        int bestStart = -1;
        int bestLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We have more than k ones, move left
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // We have exactly k ones
            if (ones == k) {

                // Remove unnecessary leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                int len = right - left + 1;

                if (len < bestLen) {
                    bestLen = len;
                    bestStart = left;
                } 
                else if (len == bestLen) {
                    String current = s.substring(left, right + 1);
                    String best = s.substring(bestStart, bestStart + bestLen);

                    if (current.compareTo(best) < 0) {
                        bestStart = left;
                    }
                }
            }
        }

        if (bestStart == -1) {
            return "";
        }

        return s.substring(bestStart, bestStart + bestLen);
    }
}