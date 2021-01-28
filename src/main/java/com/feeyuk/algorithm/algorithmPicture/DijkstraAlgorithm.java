package com.feeyuk.algorithm.algorithmPicture;

import java.util.*;

public class DijkstraAlgorithm {

    /*

    狄克斯特拉算法包含4个步骤。
            (1) 找出最便宜的节点，即可在最短时间内前往的节点。
            (2) 对于该节点的邻居，检查是否有前往它们的更短路径，如果有，就 更新其开销。
            (3) 重复这个过程，直到对图中的每个节点都这样做了。
            (4) 计算最终路径。(下一节再介绍!)

            原始数据为nodeMap,存储每个节点到子节点到权重，同时nodeMap 也作为检查列表，包含未计算到节点，节点计算以后，从nodeMap中删除
            从起点，到当前节点到总权重，存储在costMap中，;
                    ·对于costMap中不存在的节点，默认从起点到该节点花费为无限大
            花费更新到同时，需要同时更新parentMap列表，即parentMap中，保存到是当前最优花费到路线


                         a
                       > ^   \
                      /  |    \
                    6    |     1
                   /     |      \
                  /      |       >
            start        3       end
                  \      |       >
                   \     |      /
                    2    |    5
                     \   |   /
                      >  |  /
                         b


            广度优先搜索用于在非加权图中查找最短路径。
            狄克斯特拉算法用于在加权图中查找最短路径。
            仅当权重为正时狄克斯特拉算法才管用。
            如果图中包含负权边，请使用贝尔曼-福德算法。

    */

    public static final int BIGGEST = 99999;

    List<String> checkList = new ArrayList<>();
    Map<String , Map<String,Integer>> nodeMap = new HashMap<>();
    Map<String, Integer> costMap = new HashMap<>();


    Map<String, String> parentsMap = new HashMap<>();

    public void Dijkstra(){

        Map<String,Integer> startNode = new HashMap<>();
        startNode.put("a",6);
        startNode.put("b",2);
        nodeMap.put("start", startNode);

        Map<String,Integer> aNode = new HashMap<>();
        aNode.put("end",1);
        nodeMap.put("a", aNode);

        Map<String,Integer> bNode = new HashMap<>();
        bNode.put("a",3);
        bNode.put("end",5);
        nodeMap.put("b", bNode);

        Map<String,Integer> endNode = new HashMap<>();
        nodeMap.put("end", endNode);

        costMap.put("end",999);

        Set<String> keySet = nodeMap.keySet();
        Map<String,Integer> startNodesMap = nodeMap.get("start");
        for (String sonNode : startNodesMap.keySet()) {
            int oldCost = costMap.get(sonNode)==null ? 0 :costMap.get(sonNode);
            int cost = startNodesMap.get(sonNode)==null? 0 : startNodesMap.get(sonNode);

            if(oldCost == 0 || cost<oldCost){
                costMap.put(sonNode, cost);
                parentsMap.put(sonNode, "start");
            }

        }
        nodeMap.remove("start");

        //base 节点处理完了
        //recursion 处理未处理的节点，节点减一

        String item = this.findLowestNode(costMap);

        this.check(item);

        System.out.println(costMap);
        System.out.println(parentsMap);
    }

    private void check(String item){
        if(nodeMap.isEmpty()){
            return ;
        }else{
            Map<String,Integer> itemMap = nodeMap.get(item);

            for (String sonNode : itemMap.keySet()) {
                int cost = costMap.get(item);
                int newCost = cost + itemMap.get(sonNode);

                if( newCost< costMap.get(sonNode)){
                    costMap.put(sonNode, newCost);
                    parentsMap.put(sonNode, item);
                }
            }
            nodeMap.remove(item);
            item = this.findLowestNode(costMap);
            this.check(item);
        }
    }

    private String findLowestNode(Map<String, Integer> costMap) {
        int resultValue = 1000000;
        String resultKey = "";
        for (String key : costMap.keySet()) {
            int cost = costMap.get(key);
            if(cost < resultValue &&  nodeMap.containsKey(key)){
                resultValue = cost;
                resultKey = key;
            }
        }
        return resultKey;
    }

    public static void main(String[] args) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        dijkstraAlgorithm.Dijkstra();
    }


}
