package com.feeyuk.algorithm.leetcode;

import java.util.Arrays;

public class FindPivotIndex {

    /*
    * 思路
    * 1。num的length<=2，不存在
    * num的length>3
    * 1。num从num[1]开始计算，计算num[1]左侧的和sumLeft，范围从0-1 ，计算num[2]右侧的和sumRight，从2-（length-1）
    * 2。比较sumLeft ,sumRight ,如果不相等，增加索引的值，增加sumLeft值，减少sumRight的值
    * 3。结束条件为 不存在或者，存在的第一个
    * */


    public int pivotIndex(int[] nums) {
        // if(nums.length<=2){
        //     return -1;
        // }
        int sumLeft = 0;
        int sumRight = sum(nums, 1, 0);

        return this.findPivot(nums, 0,sumLeft, sumRight);
    }

    private int findPivot(int[] nums, int startIndex, int sumLeft, int sumRight) {
        if(startIndex== (nums.length-1) || sumLeft==sumRight){
            if(sumLeft==sumRight){
                return startIndex;
            }
            return -1;
        } else {
            sumLeft= sumLeft + nums[startIndex];
            startIndex +=1;
            sumRight = sumRight - nums[startIndex];
            return findPivot(nums, startIndex, sumLeft, sumRight);
        }

    }

    private int sum(int[] nums, int startIndex, int sum){
        if(startIndex== nums.length ){
            return sum;
        }else{
            sum= sum+ nums[startIndex];
            startIndex+=1;
            return sum(nums, startIndex, sum);
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 7, 3, 6, 5, 6};
        System.out.println(Arrays.stream(test).sum()-test[0]);
        FindPivotIndex findPivotIndex = new FindPivotIndex();
        int i = findPivotIndex.pivotIndex(test);
        System.out.println(i);
    }
}
