class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];

        // Build prefix maximum
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        // Build suffix minimum
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int[] ans = new int[n];

        int start = 0;

        for (int i = 0; i < n - 1; i++) {

            /*
              If all left values <= all right values,
              then no inversion exists across boundary,
              so components are separated here.
            */
            if (prefixMax[i] <= suffixMin[i + 1]) {

                // Find maximum inside current component
                int mx = Integer.MIN_VALUE;

                for (int j = start; j <= i; j++) {
                    mx = Math.max(mx, nums[j]);
                }

                // Fill answers
                for (int j = start; j <= i; j++) {
                    ans[j] = mx;
                }

                start = i + 1;
            }
        }

        // Last component
        int mx = Integer.MIN_VALUE;

        for (int j = start; j < n; j++) {
            mx = Math.max(mx, nums[j]);
        }

        for (int j = start; j < n; j++) {
            ans[j] = mx;
        }

        return ans;
    }
}