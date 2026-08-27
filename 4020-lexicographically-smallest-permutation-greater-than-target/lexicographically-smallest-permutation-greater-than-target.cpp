class Solution {
public:
    int getNext(vector<int>& cnt, int start) {
        for (int c = start + 1; c < 26; c++) {
            if (cnt[c] > 0) {
                return c;
            }
        }
        return -1;
    }

    string build(string target, int pos, int ch, vector<int>& cnt) {
        string ans = target.substr(0, pos);

        ans += char('a' + ch);

        cnt[ch]--;

        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                ans += char('a' + c);
                cnt[c]--;
            }
        }

        return ans;
    }

    string lexGreaterPermutation(string s, string target) {
        int n = s.size();

        vector<int> cnt(26, 0);

        for (char c : s) {
            cnt[c - 'a']++;
        }

        string ans = "";

        // Try to match target from left to right.
        for (int i = 0; i < n; i++) {
            int t = target[i] - 'a';

            // We can use the same character.
            if (cnt[t] > 0) {
                ans += target[i];
                cnt[t]--;
                continue;
            }

            // Same character is unavailable.
            // First try to make this position larger.
            int nxt = getNext(cnt, t);

            if (nxt != -1) {
                ans += char('a' + nxt);
                cnt[nxt]--;

                // Put all remaining characters in sorted order.
                for (int c = 0; c < 26; c++) {
                    while (cnt[c] > 0) {
                        ans += char('a' + c);
                        cnt[c]--;
                    }
                }

                return ans;
            }

            // Cannot make current position larger.
            // Backtrack to find a previous position that can be increased.
            for (int p = i - 1; p >= 0; p--) {

                int old = target[p] - 'a';

                // Restore target[p].
                cnt[old]++;

                int up = getNext(cnt, old);

                if (up != -1) {
                    string result = target.substr(0, p);

                    result += char('a' + up);
                    cnt[up]--;

                    // Fill remaining positions with smallest characters.
                    for (int c = 0; c < 26; c++) {
                        while (cnt[c] > 0) {
                            result += char('a' + c);
                            cnt[c]--;
                        }
                    }

                    return result;
                }
            }

            return "";
        }

        // target itself was constructible.
        // We need a STRICTLY greater permutation.
        for (int p = n - 1; p >= 0; p--) {

            int old = target[p] - 'a';

            // Restore target[p].
            cnt[old]++;

            int up = getNext(cnt, old);

            if (up != -1) {
                string result = target.substr(0, p);

                result += char('a' + up);
                cnt[up]--;

                // Fill remaining characters in sorted order.
                for (int c = 0; c < 26; c++) {
                    while (cnt[c] > 0) {
                        result += char('a' + c);
                        cnt[c]--;
                    }
                }

                return result;
            }
        }

        return "";
    }
};