package com.jarvi;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 * <p>
 * 时间复杂度：O(n)，其中 n 为链表的长度。head 指针会在 n/k 个节点上停留，每次停留需要进行一次 O(k) 的翻转操作。
 * 空间复杂度：O(1)，我们只需要建立常数个变量。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 参考：
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
 */
public class ReverseKGroup25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        //定义哨兵节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            //寻找待翻转子链表的尾节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //剩余节点数量少于 k，结束
            if (end == null) {
                break;
            }
            //待翻转子链表的头节点
            ListNode start = prev.next;
            //未翻转部分的头节点
            ListNode next = end.next;
            //断开待翻转子链表尾节点与后续部分的连接，方便reverse方法的终止条件判断。
            end.next = null;
            //翻转子链表
            reverse(start);
            //翻转后，原尾节点变成子链表的头节点，将其与前置节点链接
            prev.next = end;
            //翻转后，原头节点变成子链表的尾节点，将其与未翻转部分重新链接
            start.next = next;
            //初始化前置节点（重置为已翻转部分的尾节点）
            prev = start;
            //初始化尾节点（重置为已翻转部分的尾节点）
            end = start;
        }
        return dummy.next;
    }

    private void reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

}
