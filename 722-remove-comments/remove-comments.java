import java.util.*;

class Solution {
    public List<String> removeComments(String[] source) {

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean inBlock = false;

        for (String line : source) {

            int i = 0;

            if (!inBlock) sb = new StringBuilder();

            while (i < line.length()) {

                // Start of block comment
                if (!inBlock && i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '*') {
                    inBlock = true;
                    i += 2;
                }

                // End of block comment
                else if (inBlock && i + 1 < line.length() && line.charAt(i) == '*' && line.charAt(i + 1) == '/') {
                    inBlock = false;
                    i += 2;
                }

                // Line comment
                else if (!inBlock && i + 1 < line.length() && line.charAt(i) == '/' && line.charAt(i + 1) == '/') {
                    break; // ignore rest of line
                }

                // Normal character
                else if (!inBlock) {
                    sb.append(line.charAt(i));
                    i++;
                }

                // Inside block comment
                else {
                    i++;
                }
            }

            // Add only if not in block and not empty
            if (!inBlock && sb.length() > 0) {
                result.add(sb.toString());
            }
        }

        return result;
    }
}