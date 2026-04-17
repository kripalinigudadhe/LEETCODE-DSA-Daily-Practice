import java.util.*;

class Solution {
    
    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + (num % 10);
            num /= 10;
        }
        return rev;
    }
    
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            
            // If current number already exists in map
            if (map.containsKey(nums[i])) {
                minDist = Math.min(minDist, i - map.get(nums[i]));
            }
            
            // Store reversed number
            int rev = reverse(nums[i]);
            map.put(rev, i);
        }
        
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}