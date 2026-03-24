class Solution {
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int count = 0;
        while (head != null) {
            if (set.contains(head.val) &&
                (head.next == null || !set.contains(head.next.val))) {
                count++;
            }
            head = head.next;
        }
        return count;
    }
}