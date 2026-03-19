class Solution {
    public String minWindow(String s, String t) {
        
        if (s.length() < t.length()) return "";

        int[] freq = new int[128]; // ASCII

        // store frequency of t
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0, right = 0;
        int count = t.length();

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {

            char r = s.charAt(right);

            // if char needed
            if (freq[r] > 0) {
                count--;
            }

            freq[r]--;
            right++;

            // when valid window found
            while (count == 0) {

                // update answer
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char l = s.charAt(left);

                freq[l]++;

                // if removing breaks condition
                if (freq[l] > 0) {
                    count++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE 
                ? "" 
                : s.substring(start, start + minLen);
    }
}