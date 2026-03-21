import java.util.*;

class Solution {
    public List<Integer> diffWaysToCompute(String exp) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(exp.substring(0, i));
                List<Integer> right = diffWaysToCompute(exp.substring(i + 1));

                for (int l : left) {
                    for (int r : right) {
                        if (c == '+') res.add(l + r);
                        else if (c == '-') res.add(l - r);
                        else res.add(l * r);
                    }
                }
            }
        }

        if (res.isEmpty())
            res.add(Integer.parseInt(exp));

        return res;
    }
}