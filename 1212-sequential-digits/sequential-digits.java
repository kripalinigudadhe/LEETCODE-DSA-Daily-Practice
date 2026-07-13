class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();

        String digits = "123456789";

        int lowLength = String.valueOf(low).length();
        int highLength = String.valueOf(high).length();

        for (int len = lowLength; len <= highLength; len++) {

            for (int start = 0; start + len <= 9; start++) {

                int num = Integer.parseInt(digits.substring(start, start + len));

                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}