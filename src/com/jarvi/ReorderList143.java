package com.jarvi;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表
 *
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 *
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReorderList143 {

    /**
     * 快慢指针、反转链表
     *
     * 复杂度分析
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reorderList(ListNode head) {

        if(head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;
        //reverse list
        ListNode newHead = reverse(head2);

        while(newHead != null) {
            ListNode next = head.next;
            ListNode next2 = newHead.next;
            head.next = newHead;
            newHead.next = next;
            head = next;
            newHead = next2;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 线性表
     *
     * 复杂度分析
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public void reorderList2(ListNode head) {

        if(head == null || head.next == null) {
            return;
        }

        List<ListNode> list = new ArrayList<>();
        while(head != null) {
            list.add(head);
            head = head.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while(i < j) {
            ListNode next = list.get(i).next;
            list.get(i).next = list.get(j);
            list.get(j).next = next;
            i++;
            j--;
        }
        list.get(i).next = null;
    }



}
