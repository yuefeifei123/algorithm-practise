package com.feeyuk.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class PathWithMinimumEffort {
    /*
    * 这是一个狄克斯特拉问题
    * 起点为0。0；终点 为m，n
    * 每个节点 x,y 到下个节点到路径为 x+1,y 与 x,y+1
    * 权重为 高度值
    *
    *
    *
    */

    public static final int BIGGEST=999999;
    Map<String, Integer> costMap = new HashMap<>();

    Map<String, Integer> checkedMap = new HashMap<>();
    Map<String, String> parentMap = new HashMap<>();

    public int minimumEffortPath(int[][] heights) {

        //1。形成节点-子节点 权重表，考虑在遍历的时候形成
        //2。costMap表，记录到当前节点的最小体力值
        //3。对于还未到达到节点，即不在costMap中的节点，默认体力值为无限大

        //步骤
        //1。从起点开始，查找到起点邻居所用到体力值，并放入costMap中
        //3。对于每个节点，查找节点的所有未到达的邻居，并更新到邻居的最小体力值
        //    3。1 需要一个map表存储已到达的节点
        //4。当邻居是终点的时候，结束
        int row = heights.length;
        int height = heights[0].length;
        if(row==1 && height==1){
            return 0;
        }

        //key = row - height
        costMap.put("0-0",BIGGEST);

        this.runWay(heights, 0, 0);
//        System.out.println( costMap.get((row-1)+"-"+(height-1)) );
//        System.out.println(costMap);
//        System.out.println(parentMap);
        return this.generate(parentMap, row-1, height-1, heights,0 );
//        return costMap.get((heights.length-1)+"-"+(heights[0].length-1) );
    }

    private void runWay(int[][] heights, int row, int height) {
        if(row==heights.length-1 && height==heights[0].length-1){
            return ;
        } else {
            int preRow = row-1;
            int preHeight = height-1;
            int nextRow = row+1;
            int nextHeight = height+1;

            int preRowCost = BIGGEST;
            int preHeightCost = BIGGEST;
            int nextRowCost=BIGGEST;
            int nextHeightCost =BIGGEST;

            if(nextRow<=heights.length-1 ){
                nextRowCost = Math.abs(heights[nextRow][height]-heights[row][height]);
            }
            if(nextHeight<=heights[0].length-1){
                nextHeightCost = Math.abs(heights[row][nextHeight]-heights[row][height]);
            }

            if(preRow>=0){
                preRowCost = Math.abs(heights[preRow][height]-heights[row][height]);
            }
            if(preHeight>=0){
                preHeightCost = Math.abs(heights[row][preHeight]- heights[row][height]);
            }


            String current = row +"-"+ height;
            String preRowNeighbor = preRow+"-"+height;
            String preHeightNeighbor = row+"-"+preHeight;
            String nexRowNeighbor = nextRow +"-"+height;
            String nextHeightNeighbor = row + "-"+nextHeight;

            int preRowOldCost = this.getOldCost(preRowNeighbor, costMap);
            int preHeightOldCost = this.getOldCost(preHeightNeighbor, costMap);
            int nextRowOldCost = this.getOldCost(nexRowNeighbor, costMap);
            int nextHeightOldCost = this.getOldCost(nextHeightNeighbor, costMap);


            if(nextRowCost<=nextRowOldCost){
                costMap.put(nexRowNeighbor, nextRowCost);
            }

            if(nextHeightCost<=nextHeightOldCost){
                costMap.put(nextHeightNeighbor, nextHeightCost);
            }

            if(preRowCost<=preRowOldCost){
                costMap.put(preRowNeighbor, preRowCost);
            }

            if(preHeightCost<=preHeightOldCost) {
                 costMap.put(preHeightNeighbor, preHeightCost);
            }

            //todo 需要对比当前节点的所有邻居，查找最低点

            checkedMap.put(current, 1);
            //找到cost最小的邻居，继续run
            String nextNode = findNoCheckLowestNeighbor(costMap) ;
            //当前节点已检查
            parentMap.put(nextNode, current);

            row = Integer.valueOf(nextNode.split("-")[0]);
            height = Integer.valueOf(nextNode.split("-")[1]);
            this.runWay(heights, row, height);

        }
    }

    private int getOldCost(String rowHeight, Map<String, Integer> costMap) {
        int result = BIGGEST;
        if(costMap.containsKey(rowHeight)){
            return costMap.get(rowHeight);
        }
        return result;
    }

    private String findNoCheckLowestNeighbor(Map<String, Integer> costMap) {
        String nextCheckNode = "2-2";
        int pivot = BIGGEST;
        for (String node : costMap.keySet()) {
            if(costMap.get(node)< pivot && !checkedMap.containsKey(node)){
                pivot = costMap.get(node);
                nextCheckNode = node;
            }
        }
        return nextCheckNode;
    }

    private int generate(Map<String, String> parentMap, int row, int height, int[][] heights, int max){
        if(row==0 && height==0){
            return max;
        }else{
            String son = row+"-"+height;
            int sonRow = Integer.valueOf(son.split("-")[0]);
            int sonHeight = Integer.valueOf(son.split("-")[1]);
            String parent = parentMap.get(son);
            int parentRow = Integer.valueOf(parent.split("-")[0]);
            int parentHeight = Integer.valueOf(parent.split("-")[1]);

            max = Math.max(max, Math.abs(heights[sonRow][sonHeight]-heights[parentRow][parentHeight]));
            row= parentRow;
            height = parentHeight;
            return this.generate(parentMap, row, height, heights, max);
        }

    }
    public static void main(String[] args) {
//        int [][] test = {{1,2,2},{3,8,2},{5,3,5}};
//        int [][] test = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        int [][] test = {{1,10,6,7,9,10,4,9}};
//        System.out.println(test[2][2]);
        System.out.println(test[0].length);
        PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();
        System.out.println(pathWithMinimumEffort.minimumEffortPath(test));
    }
}
