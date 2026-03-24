import java.util.*;

class Solution {
    class Pair {
        int val, idx;
        Pair(int v, int i) { val = v; idx = i; }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        mergeSort(arr, 0, n - 1, count);

        List<Integer> res = new ArrayList<>();
        for (int c : count) res.add(c);
        return res;
    }

    private void mergeSort(Pair[] arr, int left, int right, int[] count) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, count);
        mergeSort(arr, mid + 1, right, count);
        merge(arr, left, mid, right, count);
    }

    private void merge(Pair[] arr, int left, int mid, int right, int[] count) {
        List<Pair> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (arr[j].val < arr[i].val) {
                rightCount++;
                temp.add(arr[j++]);
            } else {
                count[arr[i].idx] += rightCount;
                temp.add(arr[i++]);
            }
        }

        while (i <= mid) {
            count[arr[i].idx] += rightCount;
            temp.add(arr[i++]);
        }

        while (j <= right) {
            temp.add(arr[j++]);
        }

        for (int k = left; k <= right; k++) {
            arr[k] = temp.get(k - left);
        }
    }
}