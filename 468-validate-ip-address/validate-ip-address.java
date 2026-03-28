class Solution {
    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            String[] parts = IP.split("\\.", -1);
            if (parts.length != 4) return "Neither";
            
            for (String p : parts) {
                if (p.length() == 0 || (p.length() > 1 && p.charAt(0) == '0'))
                    return "Neither";
                
                try {
                    int num = Integer.parseInt(p);
                    if (num < 0 || num > 255) return "Neither";
                } catch (Exception e) {
                    return "Neither";
                }
            }
            return "IPv4";
        } 
        
        if (IP.contains(":")) {
            String[] parts = IP.split(":", -1);
            if (parts.length != 8) return "Neither";
            
            for (String p : parts) {
                if (p.length() == 0 || p.length() > 4) return "Neither";
                
                for (char c : p.toCharArray()) {
                    if (!Character.isDigit(c) &&
                        (Character.toLowerCase(c) < 'a' || Character.toLowerCase(c) > 'f'))
                        return "Neither";
                }
            }
            return "IPv6";
        }
        
        return "Neither";
    }
}