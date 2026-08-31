class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a critical point
            boolean isCritical =
                (curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {
                if (first == -1) {
                    // First critical point
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(minDistance, index - prevCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || first == prevCritical) {
            return new int[]{-1, -1};
        }

        // Maximum distance = last critical - first critical
        int maxDistance = prevCritical - first;

        return new int[]{minDistance, maxDistance};
    }
}