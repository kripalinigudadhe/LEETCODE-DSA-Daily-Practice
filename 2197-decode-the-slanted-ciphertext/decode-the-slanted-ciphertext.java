class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        
        if (rows == 1) return encodedText;
        
        int cols = n / rows;
        StringBuilder result = new StringBuilder();
        
        for (int c = 0; c < cols; c++) {
            int i = 0, j = c;
            while (i < rows && j < cols) {
                result.append(encodedText.charAt(i * cols + j));
                i++;
                j++;
            }
        }
        
        // Remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }
        
        return result.substring(0, end + 1);
    }
}