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

        ListNode oddHead = head;  // 指向奇数节点的头部
        ListNode oddCurr = oddHead;  // 指向每一轮迭代最后一个奇数节点

        ListNode evenHead = head.next;  // 指向偶数节点的头部
        ListNode evenCurr = evenHead;  // 指向每一轮迭代最后一个偶数节点

        // 每一轮迭代都交替把oddCurr.next和evenCurr.next指向下一个奇数和偶数节点
        while (true) {
            if (evenCurr.next != null) {
                oddCurr.next = evenCurr.next;
                oddCurr = oddCurr.next;
            } else {
                break;
            }

            if (oddCurr.next != null) {
                evenCurr.next = oddCurr.next;
                evenCurr = evenCurr.next;
            } else {
                break;
            }
        }

        // 最后把奇数链表的尾巴跟偶数链表的头部连接起来
        evenCurr.next = null;
        oddCurr.next = evenHead;

        return oddHead;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        ListNode node_1 = new ListNode(1);
        ListNode node_2 = new ListNode(2);
        ListNode node_3 = new ListNode(3);
        ListNode node_4 = new ListNode(4);
        ListNode node_5 = new ListNode(5);
        ListNode node_6 = new ListNode(6);

        node_1.next = node_2;
        node_2.next = node_3;
        node_3.next = node_4;
        node_4.next = node_5;
        node_5.next = node_6;

        printList(node_1);
        printList(new OddEvenList().oddEvenList(node_1));

    }

}
