package com.feeyuk.algorithm.leetcode;

import java.util.*;

/**
 * 2021-01-26
 */
public class NumberOfEquivalentDominoPairs {
    /*
    1128. 等价多米诺骨牌对的数量
    给你一个由一些多米诺骨牌组成的列表 dominoes。

    如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

    形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

    在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。


    示例：

    输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
    输出：1


    提示：

            1 <= dominoes.length <= 40000
            1 <= dominoes[i][j] <= 9

    思路&步骤：
    暴力法：
    遍历二维数组，选定参考值，比较与参考值是否是等价的；双循环，每次循环范围递减1
    关键点：
    1.有多个与参考值相同的值，考虑剔除相同的属性；
    2.考虑到相同值之间需要互相比较，不需剔除相同属性
    结果：超时；

    减少长度
    在暴力法的基础上，已经比较过的元素，进行删除
    1.多个相同值进行删除
    2.n个相同骨牌，总共可以产生 n*(n-1)/2 个结果
    3.需要考虑删除元素后的数组越界问题


    hashmap
    1。每个数组中只有两个元素，如果排序的话，一对数组就是完全相同的
    2。统计每个相同数组的个数，并计算相同对的个数 n*(n-1)/2
        2。1 简化相同组，将每个数组转化为数组，int[0]*10+int[1]
    3。计算总的对的数量
            */

    public int numEquivDominoPairs(int[][] dominoes) {
        int result = 0;
        for (int i = 0; i < dominoes.length; i++) {
            int [] pivot = dominoes[i];
            for (int j = i+1; j < dominoes.length; j++) {
                int [] item = dominoes[j];
                if(item==pivot || (item[0]==pivot[1] && item[1]==pivot[0])){
                    result+=1;
                }
            }
        }
        return result;
    }

    public int numEquivDominoPairsDelete(int[][] dominoes) {
        int result = 0;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < dominoes.length; i++) {
            list.add(dominoes[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            int [] pivot = list.get(i);
            int sameNum = 1;
            for (int j = list.size()-1; j >i ; j--) {
                int [] item = list.get(j);
                if( (item[0]==pivot[0] && item[1]==pivot[1]) || (item[0]==pivot[1] && item[1]==pivot[0])){
                    sameNum+=1;
                    list.remove(j);
                }
            }
            result = result+ sameNum*(sameNum-1)/2;
        }
        return result;
    }

    public int numEquivDominoPairsMap(int[][] dominoes) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < dominoes.length; i++) {
            int [] item = dominoes[i];
            int itemNum = 10*item[0]+item[1];
            if(item[1]>item[0])
            {
                itemNum = 10*item[1]+item[0];
            }
            int oldValue = map.get(itemNum)==null? 0 : map.get(itemNum);
            map.put(itemNum, oldValue+1);
        }
        int result =0;
        Iterator<Integer> iterator = map.values().iterator();
        while(iterator.hasNext()){
            int num = iterator.next();
            result = result + num*(num-1)/2;
        }
        return result;

    }


    public static void main(String[] args) {
        NumberOfEquivalentDominoPairs numberOfEquivalentDominoPairs = new NumberOfEquivalentDominoPairs();
//        System.out.println(numberOfEquivalentDominoPairs.numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
//        System.out.println(numberOfEquivalentDominoPairs.numEquivDominoPairsDelete(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
//        System.out.println(numberOfEquivalentDominoPairs.numEquivDominoPairsDelete(new int[][]{{1,2},{1,2},{1,1},{1,2},{2,2}}));
        System.out.println(numberOfEquivalentDominoPairs.numEquivDominoPairsMap(new int[][]{{1,2},{1,2},{1,1},{1,2},{2,2}}));


    }

}
