class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();

        paragraph = paragraph.toLowerCase().replaceAll("[^a-z ]", " ");
        String[] words = paragraph.split("\\s+");

        for (String w : words) {
            if (!ban.contains(w)) {
                map.put(w, map.getOrDefault(w, 0) + 1);
            }
        }

        String res = "";
        int max = 0;

        for (String w : map.keySet()) {
            if (map.get(w) > max) {
                max = map.get(w);
                res = w;
            }
        }
        return res;
    }
}