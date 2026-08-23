class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int sumLeft = 0;
        int sumRight = 0;
        int qLeft = 0;
        int qRight = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                sumLeft += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                sumRight += num.charAt(i) - '0';
            }
        }

        int diff = sumLeft - sumRight;

        // If total number of ? is odd,
        // Alice always has the advantage.
        if ((qLeft + qRight) % 2 == 1) {
            return true;
        }

        // Difference in number of ? between the halves
        int qDiff = qLeft - qRight;

        /*
         * Bob can force equality only when:
         *
         * diff + 9 * (qDiff / 2) == 0
         *
         * The division is integer division, and qDiff is even
         * because total number of ? is even.
         */
        return diff + 9 * (qDiff / 2) != 0;
    }
}