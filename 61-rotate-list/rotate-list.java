class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find length and tail
        ListNode curr = head;
        int length = 1;

        while (curr.next != null) {
            curr = curr.next;
            length++;
        }

        // Make it circular
        curr.next = head;

        // Step 2: Reduce k
        k = k % length;
        int stepsToNewHead = length - k;

        // Step 3: Find new tail
        ListNode newTail = curr;
        while (stepsToNewHead-- > 0) {
            newTail = newTail.next;
        }

        // Step 4: Break circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}