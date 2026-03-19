import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        backtrack(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] visited, 
                           List<Integer> temp, List<List<Integer>> result) {

        // if permutation complete
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) continue;

            // choose
            visited[i] = true;
            temp.add(nums[i]);

            // explore
            backtrack(nums, visited, temp, result);

            // backtrack (undo)
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }
}