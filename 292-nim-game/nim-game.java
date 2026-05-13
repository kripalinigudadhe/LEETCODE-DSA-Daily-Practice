class Solution {
    public boolean canWinNim(int n) {
        // If n is divisible by 4, you lose
        // otherwise, you can win
        return n % 4 != 0;
    }
}