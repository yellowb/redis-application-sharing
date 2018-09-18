package data_structure.medium;

public class OddEvenList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }

        ListNode oddHead = head;
        ListNode oddCurr = oddHead;

        ListNode evenHead = head.next;
        ListNode evenCurr = evenHead;

        while (true) {
            if (evenCurr.next != null) {
                oddCurr.next = evenCurr.next;
                oddCurr = oddCurr.next;
            }
            else {
                break;
            }

            if (oddCurr.next != null) {
                evenCurr.next = oddCurr.next;
                evenCurr = evenCurr.next;
            }
            else {
                break;
            }
        }

        evenCurr.next = null;
        oddCurr.next = evenHead;

        return oddHead;
    }

    public static void main(String[] args) {
        
    }

}
