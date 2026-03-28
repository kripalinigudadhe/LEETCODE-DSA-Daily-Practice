class Solution {
    public String fractionAddition(String expression) {
        int num = 0, den = 1;

        int i = 0;
        while (i < expression.length()) {
            int sign = 1;
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                sign = expression.charAt(i++) == '-' ? -1 : 1;
            }

            int n = 0;
            while (Character.isDigit(expression.charAt(i))) {
                n = n * 10 + (expression.charAt(i++) - '0');
            }
            i++; // skip '/'

            int d = 0;
            while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                d = d * 10 + (expression.charAt(i++) - '0');
            }

            n *= sign;

            num = num * d + n * den;
            den = den * d;

            int g = gcd(Math.abs(num), den);
            num /= g;
            den /= g;
        }

        return num + "/" + den;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}