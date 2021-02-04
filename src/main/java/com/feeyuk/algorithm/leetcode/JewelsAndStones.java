package com.feeyuk.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class JewelsAndStones {
    /*
    给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。

    J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。

    示例 1:

    输入: J = "aA", S = "aAAbbbb"
    输出: 3
    示例 2:

    输入: J = "z", S = "ZZ"
    输出: 0
    注意:

    S 和 J 最多含有50个字母。
             J 中的字符不重复。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/jewels-and-stones
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    思路:
    暴力法：
        遍临j*s
        时间复杂度 O(m*n)
        空间复杂度 O(1)

    哈希
    1.将j中的字符存在Map中，遍历s的每个char,判断是否在map中
        时间复杂度 O(m+n)
        空间复杂度 O(m)

    2.将s中的字符存在map中，并根据char进行累计和，遍历j，计算j中字符在map中的value合
        时间复杂度 O(m+n)
        空间复杂度 O(n)

    对比：
        根据m和n的值来灵活选取，1比较合理


    */



    public int numJewelsInStones(String jewels, String stones) {
        int num = 0;
        Map<Character,Integer> jewelsMap = new HashMap<>();
        for (int i = 0; i < jewels.length(); i++) {
            jewelsMap.put(jewels.charAt(i),1);
        }

        for (int i = 0; i < stones.length(); i++) {
            if(jewelsMap.containsKey(stones.charAt(i))){
                num+=1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        JewelsAndStones jewelsAndStones = new JewelsAndStones();
        System.out.println(jewelsAndStones.numJewelsInStones("z", "ZZ"));
        PriorityQueue<Integer> small = new PriorityQueue<>();
        small.offer(2);
    }

}
