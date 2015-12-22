/*
Reverse a singly linked list.
*/

// Iterative solution
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode end = head;
        while (end.next != null) {
            ListNode tmp = end.next;
            end.next = tmp.next;
            tmp.next = head;
            head = tmp;
        }
        return head;
    }
}

// Recursive solution
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode[] headEnd = new ListNode[2];
        helper(head, headEnd);
        return headEnd[0];
    }
    
    public void helper(ListNode head, ListNode[] headEnd) {
        if (head == null) return;
        if (head.next == null) {
            headEnd[0] = head;
            headEnd[1] = head;
            return;
        }
        
        helper(head.next, headEnd);
        head.next = null;
        headEnd[1].next = head;
        headEnd[1] = head;
    }
}
