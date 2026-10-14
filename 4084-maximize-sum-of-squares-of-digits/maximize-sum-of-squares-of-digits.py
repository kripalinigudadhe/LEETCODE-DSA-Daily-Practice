class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if sum < 1 or sum > 9 * num:
            return ""
        q, r = divmod(sum, 9)
        res = ['9'] * q
        if r:
            res.append(str(r))
        res.extend(['0'] * (num - len(res)))
        return ''.join(res)