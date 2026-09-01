import java.util.*;

class Solution {
    static class State {
        int r, c, mask, energy;

        State(int r, int c, int mask, int energy) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        List<int[]> litter = new ArrayList<>();

        // Find start and all litter cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        // No litter to collect
        if (k == 0) return 0;

        int fullMask = (1 << k) - 1;

        /*
         * visited[r][c][mask][energy]
         * Since energy <= 50 and litter <= 10, this is manageable.
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << k][energy + 1];

        Queue<State> q = new ArrayDeque<>();

        visited[sr][sc][0][energy] = true;
        q.offer(new State(sr, sc, 0, energy));

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                State cur = q.poll();

                if (cur.mask == fullMask) {
                    return moves;
                }

                // If energy is 0, the student can only continue
                // if currently standing on a reset cell.
                if (cur.energy == 0) {
                    if (classroom[cur.r].charAt(cur.c) == 'R') {
                        cur.energy = energy;
                    } else {
                        continue;
                    }
                }

                for (int[] d : dirs) {
                    int nr = cur.r + d[0];
                    int nc = cur.c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int newEnergy = cur.energy - 1;

                    if (newEnergy < 0) {
                        continue;
                    }

                    int newMask = cur.mask;

                    // Check if the new cell contains litter
                    for (int i = 0; i < k; i++) {
                        if (litter.get(i)[0] == nr &&
                            litter.get(i)[1] == nc) {

                            newMask |= (1 << i);
                            break;
                        }
                    }

                    // Reset energy immediately after entering R
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newMask][newEnergy]) {
                        visited[nr][nc][newMask][newEnergy] = true;
                        q.offer(new State(nr, nc, newMask, newEnergy));
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}