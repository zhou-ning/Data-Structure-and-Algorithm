package List;

/**
 * 插入排序 针对链表 输入: 4->2->1->3 输出: 1->2->3->4
 */
public class InsertSort {
    public ListNode insertionSortList(ListNode head) {
        // 哑节点
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            // 默认从dummy到pre为有序
            if (cur.val < pre.val) {
                // 发现 pre<cur,说明需要找到位置进行插入
                // 从前向后找，找到合适的位置进行插入
                ListNode p = dummy;

                while (p.next.val < cur.val) {
                    p = p.next;
                }
                // 将 cur插入到合适位置
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                cur = pre.next;

            } else {
                cur = cur.next;
                pre = pre.next;
            }

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        InsertSort s = new InsertSort();
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
        p = s.insertionSortList(head);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}