class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int max = 0, left = 0, res = 0;

        for(int right=0; right<s.length(); right++){
            count[s.charAt(right)-'A']++;
            max = Math.max(max, count[s.charAt(right)-'A']);

            while((right-left+1) - max > k){
                count[s.charAt(left)-'A']--;
                left++;
            }
            res = Math.max(res, right-left+1);
        }
        return res;
    }
}