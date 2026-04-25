#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int maxDistance(int side, vector<vector<int>>& points, int k) {
        int n = points.size();
        vector<long long> pos;

        // Step 1: Map boundary → 1D
        for (auto &p : points) {
            int x = p[0], y = p[1];

            if (y == 0) pos.push_back(x);
            else if (x == side) pos.push_back(side + y);
            else if (y == side) pos.push_back(3LL * side - x);
            else pos.push_back(4LL * side - y);
        }

        sort(pos.begin(), pos.end());
        long long perimeter = 4LL * side;

        // Step 2: Extend for circular
        vector<long long> ext = pos;
        for (auto p : pos) ext.push_back(p + perimeter);

        // Step 3: Binary Search
        long long left = 0, right = 2LL * side, ans = 0;

        while (left <= right) {
            long long mid = (left + right) / 2;
            if (canPick(ext, pos, k, mid, perimeter)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int)ans;
    }

private:
    bool canPick(vector<long long>& ext, vector<long long>& pos,
                 int k, long long dist, long long perimeter) {

        int n = pos.size();

        for (int i = 0; i < n; i++) {
            long long first = ext[i];
            long long last = first;
            int count = 1;

            int idx = i;

            while (count < k) {
                long long target = last + dist;

                // binary search next valid point
                auto it = lower_bound(ext.begin() + idx + 1,
                                      ext.begin() + i + n, target);

                if (it == ext.begin() + i + n) break;

                last = *it;
                idx = it - ext.begin();
                count++;
            }

            if (count < k) continue;

            // circular check
            if (perimeter - (last - first) >= dist) return true;
        }

        return false;
    }
};