/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode cur = head.next;

        int i = 1;

        while (cur.next != null) {

            if ((cur.val > prev.val && cur.val > cur.next.val) ||
                (cur.val < prev.val && cur.val < cur.next.val)) {

                if (first == -1) {
                    first = i;
                } else {
                    min = Math.min(min, i - last);
                }

                last = i;
            }

            prev = cur;
            cur = cur.next;
            i++;
        }

        if (first == -1 || first == last)
            return new int[]{-1, -1};

        int max = last - first;

        return new int[]{min, max};
    }
}