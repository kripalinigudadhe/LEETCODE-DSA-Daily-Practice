class Solution: 
    def uniformArray(self, nums1): 
        oddCount = 0 
        evenCount = 0 
 
        for num in nums1: 
            if num % 2 == 0: 
                evenCount += 1 
            else: 
                oddCount += 1 
 
        if oddCount == 0: 
            return True 
 
        if evenCount == 0: 
            return True 
 
        minimum = min(nums1) 
 
        if minimum % 2 == 1: 
            return True 
 
        return False