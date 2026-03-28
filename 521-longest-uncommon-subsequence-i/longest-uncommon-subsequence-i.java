class Solution {
    public int findLUSlength(String a, String b) {
        // If both strings are equal → no uncommon subsequence
        if (a.equals(b)) return -1;

        // Otherwise → max length of the two
        return Math.max(a.length(), b.length());
    }
}