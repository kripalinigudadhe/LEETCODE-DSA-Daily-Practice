import java.util.*;

class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length, sum = 0;
        for (int x : nums) sum += x;

        Arrays.sort(nums);

        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) dp.add(new HashSet<>());
        dp.get(0).add(0);

        for (int num : nums) {
            for (int i = n - 1; i >= 1; i--) {
                for (int prev : dp.get(i - 1)) {
                    dp.get(i).add(prev + num);
                }
            }
        }

        for (int i = 1; i <= n / 2; i++) {
            if ((sum * i) % n == 0 && dp.get(i).contains(sum * i / n))
                return true;
        }
        return false;
    }
}