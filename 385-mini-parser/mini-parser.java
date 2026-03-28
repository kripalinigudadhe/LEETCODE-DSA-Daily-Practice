import java.util.*;

class Solution {
    public NestedInteger deserialize(String s) {
        // If it's just a number (no brackets)
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger curr = null;
        int num = 0;
        boolean negative = false;
        boolean hasNum = false;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '[') {
                if (curr != null) stack.push(curr);
                curr = new NestedInteger();
            } 
            else if (ch == ']') {
                if (hasNum) {
                    curr.add(new NestedInteger(negative ? -num : num));
                }

                if (!stack.isEmpty()) {
                    NestedInteger parent = stack.pop();
                    parent.add(curr);
                    curr = parent;
                }

                // reset
                num = 0;
                negative = false;
                hasNum = false;
            } 
            else if (ch == ',') {
                if (hasNum) {
                    curr.add(new NestedInteger(negative ? -num : num));
                }

                // reset
                num = 0;
                negative = false;
                hasNum = false;
            } 
            else if (ch == '-') {
                negative = true;
            } 
            else { // digit
                num = num * 10 + (ch - '0');
                hasNum = true;
            }
        }

        return curr;
    }
}