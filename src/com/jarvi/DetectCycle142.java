package com.jarvi;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle142 {

    public ListNode detectCycle(ListNode head) {
       //TODO
        return null;
    }

    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

}
