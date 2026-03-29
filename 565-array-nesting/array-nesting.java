class Solution {
    public int arrayNesting(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = nums[j];
                count++;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}