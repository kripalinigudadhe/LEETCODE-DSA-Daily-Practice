class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[][] pos = new int[26][2];

        for (int i = 0; i < 26; i++)
            pos[i] = new int[]{-1, -1};

        int res = 0;

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'A';
            res += (i - pos[c][1]) * (pos[c][1] - pos[c][0]);
            pos[c][0] = pos[c][1];
            pos[c][1] = i;
        }

        for (int c = 0; c < 26; c++) {
            res += (n - pos[c][1]) * (pos[c][1] - pos[c][0]);
        }

        return res;
    }
}