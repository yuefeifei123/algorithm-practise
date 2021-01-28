package com.feeyuk.algorithm.leetcode;

import java.util.Stack;

public class AddTwoNumbers {
/*    2. 两数相加
    给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

    请你将两个数相加，并以相同形式返回一个表示和的链表。

    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    2 -> 4 -> 3
    5 -> 6 -> 4
    -----------
    7 -> 0 -> 8

    示例 1：


    输入：l1 = [2,4,3], l2 = [5,6,4]
    输出：[7,0,8]
    解释：342 + 465 = 807.
    示例 2：

    输入：l1 = [0], l2 = [0]
    输出：[0]
    示例 3：

    输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    输出：[8,9,9,9,0,0,0,1]


    提示：

    每个链表中的节点数在范围 [1, 100] 内
0 <= Node.val <= 9
    题目数据保证列表表示的数字不含前导零

    思路：
    递归想加
    base 两个node的长度均为0
    recursion node的next进行计算
                node的val进行想加；sum%10 为当前值； sum/10为进位值，下一位计算的时候需要使用；
                存储结果：最简单使用listNode进行存储，
                ps：第一次尝试使用listNode，但是失败了，所以第一次使用的栈进行存储；
                    第二次使用listNode,需要保证listNode作用域大于递归方法的作用域


    */




    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
        ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void printListNode(ListNode listNode){

        if(null==listNode.next){
            return ;
        }else {
            listNode = listNode.next;
            this.printListNode(listNode);
        }
    }

    /**初版解法
     * @param listNode1
     * @param listNode2
     * @return
     */
    public ListNode addTwoNumBeta(ListNode listNode1, ListNode listNode2){
        Stack<Integer> result = this.addTwoNumStack(listNode1, listNode2, new Stack<>());
        return this.stack2ListNode(result, new ListNode());
    }

    /**优化解法
     * @param listNode1
     * @param listNode2
     * @return
     */
    public ListNode addTwoNumBest(ListNode listNode1, ListNode listNode2){
        return this.addTwoNum(listNode1, listNode2, null, null, 0);
    }

    private ListNode addTwoNum(ListNode listNode1, ListNode listNode2, ListNode head, ListNode tail, int carry) {
        if(null==listNode1  && null==listNode2){
            if(carry!=0){
                ListNode last = new ListNode(carry);
                tail.next= last;
                tail=tail.next;
            }
            return head;
        }else {
            int value1 = listNode1==null? 0 : listNode1.val;
            int value2 = listNode2==null? 0 : listNode2.val;
            //进位值

            int value = value1 + value2 +carry;
            int resultValue = value%10;
            //下一位的carry
            carry = value/10;
            ListNode result = new ListNode(resultValue);

            if(head==null){
                //第一个node
                head = tail = result;
            }else{
                //非第一个node，插入node，并更改tail指向最后一个node
                tail.next = result;
                tail=tail.next;
            }

            listNode1 = listNode1==null? null : listNode1.next;
            listNode2 = listNode2==null? null : listNode2.next;

            return this.addTwoNum(listNode1, listNode2, head, tail, carry);
        }
    }

    private Stack<Integer> addTwoNumStack(ListNode listNode1, ListNode listNode2, Stack<Integer> resultStack){
        if(null==listNode1  && null==listNode2){
            //最后的进位值，不为零需保留
            if(resultStack.peek()==0){
                resultStack.pop();
            }
            return resultStack;
        }else {
            int value1 = listNode1==null? 0 : listNode1.val;
            int value2 = listNode2==null? 0 : listNode2.val;
            //进位值，从栈顶拿出
            int old = resultStack.empty()? 0 : resultStack.pop() ;
            int value = value1 + value2 +old;
            resultStack.add(value%10);
            resultStack.add(value/10);

            listNode1 = listNode1==null? null : listNode1.next;
            listNode2 = listNode2==null? null : listNode2.next;

            return this.addTwoNumStack(listNode1, listNode2, resultStack);
        }
    }

    private ListNode stack2ListNode(Stack<Integer> stack, ListNode listNode){
        if(stack.isEmpty()){
            return listNode.next;
        }else {
            ListNode preNode = new ListNode();
            int value = stack.pop();
            listNode.val = value;
            preNode.next = listNode;
            return this.stack2ListNode(stack,preNode);
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(7);

        listNode.next = listNode1;
        listNode1.next = listNode2;


        ListNode listNodeTwo = new ListNode(5);
        ListNode listNodeTwo1 = new ListNode(6);
        ListNode listNodeTwo2 = new ListNode(3);

        listNodeTwo.next = listNodeTwo1;
        listNodeTwo1.next = listNodeTwo2;


        ListNode listNode9 = new ListNode(9);
        listNode9.next=listNode9;

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode listNode3 = addTwoNumbers.addTwoNumBeta(listNode, listNodeTwo);
        ListNode listNode4 = addTwoNumbers.addTwoNumBest(listNode, listNodeTwo);
        System.out.println(listNode3);

    }

}
