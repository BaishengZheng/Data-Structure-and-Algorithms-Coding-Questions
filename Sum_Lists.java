/*
Have two *positive* numbers represented by a linked list, where each node contains a single digit.
Suppose the digits are stored in forward order.
For example:
    6 -> 1 -> 7
+   2 -> 9 -> 5
    9 -> 1 -> 2
*/

public class Solution {
    public ListNode sumLists(ListNode l1, ListNode l2) {
        int len1 = listLength(l1);
        int len2 = listLength(l2);
        
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }
        
        PartialSum sum = helper(l1, l2);
        if (sum.carry > 0) {
            ListNode res = insertBefore(sum.sum, sum.carry);
            return res;
        } else {
            return sum.sum
        }
    }
    
    // list1 and list2 have same length now
    private PartialSum helper(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        
        PartialSum sum = helper(l1.next, l2.next);
        
        int value = sum.carry + l1.value, l2.value;
        
        ListNode res = insertBefore(sum.sum, value % 10);
        
        sum.carry = value / 10;
        sum.sum = res;
        return sum;
    }
    
    private ListNode padList(ListNode l, int padding) {
        ListNode head = l;
        while (padding > 0) {
            head = insertBefore(head, 0);
            padding--;
        }
        return head;
    }
    
    private ListNode insertBefore(ListNode node, int value) {
        ListNode n = new ListNode(value);
        if (node != null) {
            n.next = node;
        }
        return n;
    }
    
    private int listLength(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}

class PartialSum {
    public ListNode sum = null;
    public int carry = 0;
}

// Another solution to this question could use two stacks, but it might use a lot space.
// And also if the number is not very large and the result is not very large, we could change one linked list 
// into one number, and then sum these two numbers, at the end just change the result number back into a linked list
