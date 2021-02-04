package com.feeyuk.algorithm.leetcode;

public class RemoveLinkedListElements {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode removeElements(ListNode head, int val) {

        this.deleteNodeByVal(head, val);
        return head;
    }

    public void deleteNodeByVal(ListNode node, int val){
        if(node.next==null){
            return ;
        }else{
            ListNode next = node.next;
            if(node.val == val){
                node.val = next.val;
                node.next = next.next;
            }
            node = next;
            this.deleteNodeByVal(node, val);
        }
    }

    public static void main(String[] args) {
        RemoveLinkedListElements removeLinkedListElements = new RemoveLinkedListElements();
//        removeLinkedListElements.removeElements()
    }


}
