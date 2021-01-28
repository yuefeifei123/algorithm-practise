package com.feeyuk.algorithm.algorithmPicture;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) throws Exception{
        Integer [] nums = {1, 3, 5, 9, 22, 33, 88, 99, 101, 105, 110};
//        List<Integer> sortedList = (List<Integer>) Arrays.asList(nums);
        List<Integer> integerList = Arrays.asList(nums);
        System.out.println(integerList);

        //do binary search
        Integer targetNum = 3;

        System.out.println(BinarySearch.binarySearch(integerList, targetNum));

        targetNum=8;

        System.out.println(BinarySearch.LeftBinarySearch(integerList, targetNum));

        List list = new LinkedList();

    }

    public static Integer binarySearch(List<Integer> integerList, Integer targetNum){

        Integer lowIndex = 0;
        Integer highIndex = integerList.size()-1;

        int resultIndex = -1;

        while(lowIndex <= highIndex){
            Integer midIndex = (lowIndex+highIndex)/2;

            if(targetNum == integerList.get(midIndex)){
                resultIndex = midIndex;
                break;
            }

            if(targetNum < integerList.get(midIndex) ){
                highIndex = midIndex-1;
                /*
                为什么是 midIndex-1 而不是 midIndex

                因为midIndex已经参与过比较，所以下次比较的时候，不需要进行比较，从midIndex-1开始比较即可
                同理，lowIndex+1

                https://www.cnblogs.com/kyoner/p/11080078.html
                * */

                continue;
            }

            if(targetNum > integerList.get(midIndex)){
                lowIndex= midIndex+1;
                continue;
            }
            return resultIndex;
        }
        return resultIndex;
    }


    public static Integer LeftBinarySearch(List<Integer> integerList, Integer targetNum){

        if (integerList.size() == 0) return -1;
        int left = 0;
        int right = integerList.size(); // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (integerList.get(mid) == targetNum) {
                right = mid;
            } else if (integerList.get(mid) < targetNum) {
                left = mid + 1;
            } else if (integerList.get(mid) > targetNum) {
                right = mid; // 注意
            }
        }
        return left;
    }
}
