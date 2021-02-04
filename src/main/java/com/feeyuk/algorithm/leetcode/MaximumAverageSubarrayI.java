package com.feeyuk.algorithm.leetcode;

public class MaximumAverageSubarrayI {
    /*
    *
    * 643. 子数组最大平均数 I
        给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。



        示例：

        输入：[1,12,-5,-6,50,3], k = 4
        输出：12.75
        解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75


        提示：

        1 <= k <= n <= 30,000。
        所给数据范围 [-10,000，10,000]。
        *
        *
        * 思路
        * k数组的最大平均数，即为k数组的最大和/k，即为长度为k的数组的最大和
        * 初始max
        * i～i+k数组的sum i
        * 滑动窗口 i+1~ i+1+k;计算sum i+1
        * max(sum i , sum i+1)
        * 最后输出max/k
        *
    * */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum= sum+nums[i];
        }
        int max = sum;
        for (int i = 0; i < nums.length-k; i++) {

            sum=sum+nums[i+k]-nums[i];
            max = Math.max(sum,max);
        }
        return (double) max/k;
    }

    public static void main(String[] args) {
        int [] nums = {1,12,-5,-6,50,3};
        MaximumAverageSubarrayI maximumAverageSubarrayI = new MaximumAverageSubarrayI();
        System.out.println(maximumAverageSubarrayI.findMaxAverage(nums, 4));

    }

}
