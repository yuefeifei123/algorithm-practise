package com.feeyuk.algorithm.leetcode;

public class DeleteNodeInALinkedList {

    /**
     * 删除节点思路
     *
     * 指向待删除node的node，指向的next node置为待删除节点指向的node
     * 待删除的节点，指向的next node 置为null;
     *
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */



    public class ListNode {
         int val;
         ListNode next;
        ListNode(int x) { val = x; }
      }

    class Solution {
        public void deleteNode(ListNode node) {
            int pivot = node.val;

            
        }
    }
}
