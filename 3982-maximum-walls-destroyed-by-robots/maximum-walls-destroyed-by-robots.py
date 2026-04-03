from bisect import bisect_left, bisect_right

class Solution:
    def maxWalls(self, robots, distance, walls):
        n = len(robots)

        # Step 1: sort robots with distance
        robots_data = sorted(zip(robots, distance))
        walls.sort()

        # Step 2: build intervals (in terms of wall indices)
        left = []
        right = []

        for i in range(n):
            pos, dist = robots_data[i]

            # LEFT interval
            L = pos - dist
            if i > 0:
                L = max(L, robots_data[i - 1][0] + 1)

            l_idx = bisect_left(walls, L)
            r_idx = bisect_right(walls, pos) - 1
            left.append((l_idx, r_idx))

            # RIGHT interval
            R = pos + dist
            if i < n - 1:
                R = min(R, robots_data[i + 1][0] - 1)

            l_idx = bisect_left(walls, pos)
            r_idx = bisect_right(walls, R) - 1
            right.append((l_idx, r_idx))

        # Step 3: DP
        dp = [[0, 0] for _ in range(n)]
        last = [[-1, -1] for _ in range(n)]

        for i in range(n):
            for d in range(2):  # 0 = left, 1 = right
                l, r = left[i] if d == 0 else right[i]

                count = (r - l + 1) if l <= r else 0

                if i == 0:
                    dp[i][d] = count
                    last[i][d] = r
                else:
                    for prev in range(2):
                        prev_last = last[i - 1][prev]

                        add = 0
                        if l <= r:
                            if l > prev_last:
                                add = r - l + 1
                            elif r > prev_last:
                                add = r - prev_last

                        if dp[i][d] < dp[i - 1][prev] + add:
                            dp[i][d] = dp[i - 1][prev] + add
                            last[i][d] = max(prev_last, r)

        return max(dp[n - 1])