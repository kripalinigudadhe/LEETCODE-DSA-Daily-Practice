import java.util.*;

class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    // Parses an expression until '}' or end of string
    private Set<String> parse(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (i < s.length() && s.charAt(i) != '}') {
            char ch = s.charAt(i);

            if (ch == ',') {
                // Union current expression into result
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            } 
            else if (ch == '{') {
                i++; // skip '{'
                Set<String> inside = parse(s);
                i++; // skip '}'

                current = combine(current, inside);
            } 
            else {
                // Single lowercase letter
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = combine(current, letter);
                i++;
            }
        }

        result.addAll(current);
        return result;
    }

    // Cartesian product + concatenation
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}