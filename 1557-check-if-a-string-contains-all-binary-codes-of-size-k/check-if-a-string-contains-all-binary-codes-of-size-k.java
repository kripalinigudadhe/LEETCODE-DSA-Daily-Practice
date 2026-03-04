import java.util.*;

class Solution {
    public boolean hasAllCodes(String s, int k) {

        int total = 1 << k;
        boolean[] seen = new boolean[total];
        int count = 0;
        int num = 0;

        for (int i = 0; i < s.length(); i++) {

            num = ((num << 1) & (total - 1)) | (s.charAt(i) - '0');

            if (i >= k - 1 && !seen[num]) {
                seen[num] = true;
                count++;

                if (count == total) {
                    return true;
                }
            }
        }

        return false;
    }
}