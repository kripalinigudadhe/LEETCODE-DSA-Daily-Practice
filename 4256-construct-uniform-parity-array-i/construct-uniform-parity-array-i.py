class Solution(object):
    def uniformArray(self, nums1):
        oddcount=0
        evencount=0

        for num in nums1:
            if num%2==0:
                evencount+=1
            else:
                oddcount+=1

        if evencount==len(nums1):
            return(True)
        if oddcount>0:
            return(True)
        return False
        