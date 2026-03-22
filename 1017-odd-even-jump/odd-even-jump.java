class Solution {
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        boolean[] odd = new boolean[n];
        boolean[] even = new boolean[n];

        odd[n - 1] = even[n - 1] = true;
        int res = 1;

        java.util.TreeMap<Integer, Integer> map = new java.util.TreeMap<>();
        map.put(arr[n - 1], n - 1);

        for (int i = n - 2; i >= 0; i--) {
            Integer hi = map.ceilingKey(arr[i]);
            Integer lo = map.floorKey(arr[i]);

            if (hi != null) odd[i] = even[map.get(hi)];
            if (lo != null) even[i] = odd[map.get(lo)];

            if (odd[i]) res++;
            map.put(arr[i], i);
        }

        return res;
    }
}