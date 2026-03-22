class Solution {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int x : arr2) set.add(x);

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(-1, 0);

        for (int a : arr1) {
            Map<Integer, Integer> next = new HashMap<>();
            for (int prev : dp.keySet()) {
                int cost = dp.get(prev);

                if (a > prev)
                    next.put(a, Math.min(next.getOrDefault(a, Integer.MAX_VALUE), cost));

                Integer higher = set.higher(prev);
                if (higher != null)
                    next.put(higher, Math.min(next.getOrDefault(higher, Integer.MAX_VALUE), cost + 1));
            }
            dp = next;
        }

        int res = Integer.MAX_VALUE;
        for (int v : dp.values()) res = Math.min(res, v);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}