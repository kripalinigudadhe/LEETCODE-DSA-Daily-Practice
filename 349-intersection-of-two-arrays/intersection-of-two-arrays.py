class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        nums2_set = set(nums2)
        unique= set()
        
        for i in nums1:
            if i in nums2_set:
                unique.add(i)
        return list(unique)        