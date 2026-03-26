from collections import defaultdict

class Solution:
    def canPartitionGrid(self, grid):
        m, n = len(grid), len(grid[0])

        total = sum(sum(row) for row in grid)

        # Store positions of values
        pos = defaultdict(list)
        for i in range(m):
            for j in range(n):
                pos[grid[i][j]].append((i, j))

        # -------- Horizontal Cuts --------
        top_sum = 0
        for i in range(m - 1):
            for j in range(n):
                top_sum += grid[i][j]

            bottom_sum = total - top_sum

            if self.valid(pos, grid, 0, i, 0, n - 1,
                          i + 1, m - 1, 0, n - 1,
                          top_sum, bottom_sum):
                return True

        # -------- Vertical Cuts --------
        left_sum = 0
        for j in range(n - 1):
            for i in range(m):
                left_sum += grid[i][j]

            right_sum = total - left_sum

            if self.valid(pos, grid, 0, m - 1, 0, j,
                          0, m - 1, j + 1, n - 1,
                          left_sum, right_sum):
                return True

        return False

    def valid(self, pos, grid,
              r1, r2, c1, c2,
              r3, r4, c3, c4,
              sum1, sum2):

        if sum1 == sum2:
            return True

        diff = abs(sum1 - sum2)

        if sum1 > sum2:
            return self.can_remove(pos, diff, r1, r2, c1, c2)
        else:
            return self.can_remove(pos, diff, r3, r4, c3, c4)

    def can_remove(self, pos, target, r1, r2, c1, c2):
        if target not in pos:
            return False

        rows = r2 - r1 + 1
        cols = c2 - c1 + 1

        for i, j in pos[target]:
            if r1 <= i <= r2 and c1 <= j <= c2:

                # 2D grid → always safe
                if rows > 1 and cols > 1:
                    return True

                # single row → only edges
                if rows == 1:
                    if j == c1 or j == c2:
                        return True

                # single column → only edges
                if cols == 1:
                    if i == r1 or i == r2:
                        return True

        return False