class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        
        for (int i = s.length() - 1; i > 0; i--) {
            
            int currentBit = (s.charAt(i) - '0') + carry;
            
            if (currentBit % 2 == 0) {
                
                steps += 1;
            } else {
                
                steps += 2;
                carry = 1;  
            }
        }
        
        
        return steps + carry;
    }
}