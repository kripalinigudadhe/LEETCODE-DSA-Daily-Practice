class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return mergeSort(prefix, 0, prefix.length - 1, lower, upper);
    }

    private int mergeSort(long[] arr, int left, int right, int lower, int upper) {
        if (left >= right) return 0;

        int mid = (left + right) / 2;
        int count = mergeSort(arr, left, mid, lower, upper)
                  + mergeSort(arr, mid + 1, right, lower, upper);

        int j = mid + 1, k = mid + 1, t = mid + 1;
        long[] temp = new long[right - left + 1];
        int r = 0;

        for (int i = left; i <= mid; i++) {
            while (k <= right && arr[k] - arr[i] < lower) k++;
            while (j <= right && arr[j] - arr[i] <= upper) j++;
            count += j - k;
        }

        int i = left;
        j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[r++] = arr[i++];
            else temp[r++] = arr[j++];
        }
        while (i <= mid) temp[r++] = arr[i++];
        while (j <= right) temp[r++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
        return count;
    }
}