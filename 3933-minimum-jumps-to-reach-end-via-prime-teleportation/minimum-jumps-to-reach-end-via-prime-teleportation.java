import java.util.*;

class Solution {

    public int minJumps(int[] nums) {
        int n = nums.length;

        // prime factor -> indices divisible by that prime
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> factors = getPrimeFactors(nums[i]);

            for (int p : factors) {
                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.offer(0);
        visited[0] = true;

        int jumps = 0;

        // avoid processing same prime multiple times
        Set<Integer> usedPrime = new HashSet<>();

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int idx = q.poll();

                if (idx == n - 1)
                    return jumps;

                // move left
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    q.offer(idx - 1);
                }

                // move right
                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.offer(idx + 1);
                }

                // teleportation
                if (isPrime(nums[idx])) {

                    int p = nums[idx];

                    if (!usedPrime.contains(p)) {

                        usedPrime.add(p);

                        List<Integer> next = map.getOrDefault(p, new ArrayList<>());

                        for (int ni : next) {

                            if (!visited[ni]) {
                                visited[ni] = true;
                                q.offer(ni);
                            }
                        }
                    }
                }
            }

            jumps++;
        }

        return -1;
    }

    // check prime
    private boolean isPrime(int x) {

        if (x < 2)
            return false;

        for (int i = 2; i * i <= x; i++) {

            if (x % i == 0)
                return false;
        }

        return true;
    }

    // unique prime factors
    private List<Integer> getPrimeFactors(int x) {

        List<Integer> list = new ArrayList<>();

        for (int p = 2; p * p <= x; p++) {

            if (x % p == 0) {

                list.add(p);

                while (x % p == 0)
                    x /= p;
            }
        }

        if (x > 1)
            list.add(x);

        return list;
    }
}