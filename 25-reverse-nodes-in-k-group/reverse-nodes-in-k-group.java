class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Step 1: check if at least k nodes exist
        ListNode curr = head;
        int count = 0;
        
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        // If we have k nodes → reverse
        if (count == k) {
            ListNode prev = null;
            curr = head;
            ListNode next = null;
            
            // Step 2: reverse k nodes
            for (int i = 0; i < k; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            
            // Step 3: recursive call for remaining list
            head.next = reverseKGroup(curr, k);
            
            return prev; // new head
        }
        
        return head; // less than k nodes → no change
    }
}