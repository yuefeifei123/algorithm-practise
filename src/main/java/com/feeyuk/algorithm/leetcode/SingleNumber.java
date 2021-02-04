package com.feeyuk.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            if(map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else{
                map.put(nums[i], 1);
            }
        }
        return map.keySet().iterator().next();
    }


}
