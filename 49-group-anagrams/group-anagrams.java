import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            
            // convert to char array and sort
            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            String key = new String(arr);

            // add to map
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}