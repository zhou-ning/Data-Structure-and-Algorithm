package List;

/**
 * 归并排序 针对链表 输入: 4->2->1->3 输出: 1->2->3->4
 */
public class MergeSort {
    // 归并排序
    public ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        }
        // 使用快慢指针找到中点
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(slow);
        return merge(l, r);
    }

    // 两条链表融合
    ListNode merge(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode p3 = dummy;
        while (p1 != null || p2 != null) {
            int v1 = p1 == null ? Integer.MAX_VALUE : p1.val;
            int v2 = p2 == null ? Integer.MAX_VALUE : p2.val;
            if (v1 < v2) {
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        ListNode head = new ListNode(4);
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(3);
        head.next = a;
        a.next = b;
        b.next = c;
        System.out.println("排序前");
        ListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
        System.out.println("插入排序后");
        p = m.mergeSort(head);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}