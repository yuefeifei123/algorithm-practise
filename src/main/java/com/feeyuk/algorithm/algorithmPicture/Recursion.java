package com.feeyuk.algorithm.algorithmPicture;

import java.util.*;

public class Recursion {


    public  void printNum(int num){
        if(num>0) {
            System.out.println(num);
            this.printNum(num-1);
        } else {
            return ;
        }
    }

    public int sumNum(Stack<Integer> integerStack){
        //base
        int sum= 0;
        if(integerStack.size()==0){
            return sum ;
        }else {
            sum = integerStack.pop() + this.sumNum(integerStack);
            return sum;
        }
    }

    public int stackSizeCount(Stack<Integer> integerStack){
        //base
        int count= 0;
        if(integerStack.empty()){
            return count ;
        }else {
            integerStack.pop();
            count = 1+ stackSizeCount(integerStack);
            return count;
        }
    }

    public int stackMaxNum(Stack<Integer> integerStack){
        //base
        int max= 0;
        if(integerStack.size()==1){
            max = integerStack.pop();
            return max ;
        }else {
            max = Math.max(integerStack.pop(), stackMaxNum(integerStack)) ;
            return max;

        }
    }

    public List<Integer> quickSort(List<Integer> needSortList){

        if(needSortList.size()<=1){
            System.out.println("-----");
            return needSortList;
        }else {
            List<Integer> sortedList = new ArrayList<>();
            int pivotIndex = (needSortList.size()-1)/2;
//            System.out.println("pivotIndex="+pivotIndex);
            int pivot = needSortList.get(pivotIndex);

            List<Integer> lowPivotList = new ArrayList<>();
            List<Integer> highPivotList = new ArrayList<>();

            for (int i = 0; i < needSortList.size()  ; i++) {
                /*不能添加pivot参与排序，否则会造成lowPivotList或者highPivotList会因为基准值一直在待排序列表中，列表未发生变化，
                递归的参数每一次需要有变化
                需要分成 low pivot high 三个队列*/
                if(needSortList.get(i)<pivot){
                    lowPivotList.add(needSortList.get(i));
                }else if(needSortList.get(i)>pivot){
                    highPivotList.add(needSortList.get(i));
                }

            }
            System.out.println("lowPivotList="+lowPivotList);
            System.out.println("highPivotList="+highPivotList);
            System.out.println("=====");
            sortedList.addAll(quickSort(lowPivotList));
            sortedList.add(pivot);
            sortedList.addAll(quickSort(highPivotList));
            return sortedList;
        }
    }


    public static void main(String[] args) {
        Recursion recursion = new Recursion();
//        recursion.printNum(4);

        Integer [] nums = {1, 3, 5, 7, 9, 22, 33, 99, 101, 105, 110,6};

//        Stack<Integer> integerStack = new Stack<>();
//        integerStack.addAll(Arrays.asList(nums));
//        System.out.println(diGui.sumNum(integerStack));
//
//        Stack<Integer> stackSize = new Stack<>();
//        stackSize.addAll(Arrays.asList(nums));
//        System.out.println(diGui.stackSizeCount(stackSize));
//
//        Stack<Integer> maxStack = new Stack<>();
//        maxStack.addAll(Arrays.asList(nums));
//        System.out.println(diGui.stackMaxNum(maxStack));

        List<Integer> quickSortList = new ArrayList<>();
        quickSortList.addAll(Arrays.asList(nums));
        System.out.println(recursion.quickSort(quickSortList));



    }

}
