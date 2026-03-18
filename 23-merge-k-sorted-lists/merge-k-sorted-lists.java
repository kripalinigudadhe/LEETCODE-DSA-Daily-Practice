import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Min heap based on node value
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Step 1: add first node of each list
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        // Dummy node to build result
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Step 2: process heap
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            // add next node of same list
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }
}