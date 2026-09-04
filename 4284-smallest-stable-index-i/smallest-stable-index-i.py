class Solution:
    def firstStableIndex(self, nums, k):
        n = len(nums)

        # Minimum from i to the end
        minRight = [0] * n
        minRight[n - 1] = nums[n - 1]

        for i in range(n - 2, -1, -1):
            minRight[i] = min(nums[i], minRight[i + 1])

        # Maximum from the beginning to i
        maxLeft = nums[0]

        for i in range(n):
            maxLeft = max(maxLeft, nums[i])

            if maxLeft - minRight[i] <= k:
                return i

        return -1