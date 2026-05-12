import java.util.*;

class Solution {
    public int minimumEffort(int[][] tasks) {

        // Sort by (minimum - actual) descending
        Arrays.sort(tasks, (a, b) ->
            (b[1] - b[0]) - (a[1] - a[0])
        );

        int ans = 0;
        int currEnergy = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            // If current energy is less than required minimum,
            // add extra energy to initial answer
            if (currEnergy < minimum) {
                ans += (minimum - currEnergy);
                currEnergy = minimum;
            }

            // Finish task
            currEnergy -= actual;
        }

        return ans;
    }
}