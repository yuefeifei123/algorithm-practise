package com.feeyuk.algorithm.leetcode;

public class ClimbingStairs {

    /*
    * 思路：
    * 爬n层楼梯，有两种方法，及从n-1爬一层到n；或n-2爬2层到n；
    * f(n)=f(n-1)+f(n-2)
    * 典型的递归问题
    *
    * 优化：
    * 使用递归会重复计算，导致使用的时间很长
    *
    * 递归转为循环
    * 从1计算到n的所有方法
    *
    * */

    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }else{
            return this.climbStairs(n-1)+this.climbStairs(n-2);
        }
    }

    public int climbStairsBest(int n){
        if(n<=2) {
            return n;
        }
        int[] num = new int[n+1];
        num[1]=1;
        num[2]=2;
        for (int i = 3; i < n+1; i++) {
            num[i]=num[i-1]+num[i-2];
        }
        return num[n];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(20));
        System.out.println(climbingStairs.climbStairsBest(20));
    }
}
