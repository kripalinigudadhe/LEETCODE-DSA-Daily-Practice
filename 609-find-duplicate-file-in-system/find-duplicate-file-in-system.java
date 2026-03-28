import java.util.*;

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] parts = path.split(" ");
            String dir = parts[0];

            for (int i = 1; i < parts.length; i++) {
                String file = parts[i];
                int idx = file.indexOf('(');

                String name = file.substring(0, idx);
                String content = file.substring(idx + 1, file.length() - 1);

                map.computeIfAbsent(content, k -> new ArrayList<>())
                   .add(dir + "/" + name);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> list : map.values()) {
            if (list.size() > 1) res.add(list);
        }

        return res;
    }
}