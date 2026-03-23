import java.util.*;

class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);

        int min = 2;
        if (arr[2] - arr[0] == 2) min = 0;
        else if (arr[1] - arr[0] <= 2 || arr[2] - arr[1] <= 2) min = 1;

        int max = (arr[2] - arr[0]) - 2;

        return new int[]{min, max};
    }
}