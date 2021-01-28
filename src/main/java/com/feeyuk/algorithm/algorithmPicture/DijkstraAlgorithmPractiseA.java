package com.feeyuk.algorithm.algorithmPicture;

import java.util.*;

public class DijkstraAlgorithmPractiseA {

    /*

    狄克斯特拉算法包含4个步骤。
            (1) 找出最便宜的节点，即可在最短时间内前往的节点。
            (2) 对于该节点的邻居，检查是否有前往它们的更短路径，如果有，就 更新其开销。
            (3) 重复这个过程，直到对图中的每个节点都这样做了。
            (4) 计算最终路径。(下一节再介绍!)



    */

    public static final int BIGGEST = 99999;


    Map<String , Map<String,Integer>> nodeMap = new HashMap<>();

    Map<String, Integer> costMap = new HashMap<>();

    Map<String, String> parentsMap = new HashMap<>();

    public void Dijkstra(){

        Map<String,Integer> startNode = new HashMap<>();
        startNode.put("a",5);
        startNode.put("b",2);
        nodeMap.put("start", startNode);

        Map<String,Integer> aNode = new HashMap<>();
        aNode.put("c",4);
        aNode.put("d",2);
        nodeMap.put("a", aNode);

        Map<String,Integer> bNode = new HashMap<>();
        bNode.put("a",8);
        bNode.put("d",7);
        nodeMap.put("b", bNode);

        Map<String,Integer> cNode = new HashMap<>();
        cNode.put("d",6);
        cNode.put("end",3);
        nodeMap.put("c", cNode);

        Map<String,Integer> dNode = new HashMap<>();
        dNode.put("end",1);
        nodeMap.put("d", dNode);

        Map<String,Integer> endNode = new HashMap<>();
        nodeMap.put("end", endNode);

        costMap.put("start",0);

        //base 节点处理完了
        //recursion 处理未处理的节点，节点减一

        String item = this.findLowestNode(costMap);

        this.check(item);


        System.out.println(costMap);
        System.out.println(parentsMap);
        System.out.println(this.generateShortestWay("end", ""));

    }

    private void check(String node){
        if(nodeMap.isEmpty()){
            return ;
        }else{
            Map<String,Integer> sonNodeMap = nodeMap.get(node);

            for (String sonNode : sonNodeMap.keySet()) {
                //对于未知的节点，从起点至该节点的花费为无穷大；已知节点为costMap中记录的值
                int sonNodeCost = costMap.get(sonNode)==null? BIGGEST : costMap.get(sonNode);

                //父节点的花费
                int nodeCost = costMap.get(node);

                //新花费为 起点至该节点父节点的花费 + 父节点至该节点的花费
                int newSonNodeCost = nodeCost + sonNodeMap.get(sonNode);

                if( newSonNodeCost< sonNodeCost ){
                    costMap.put(sonNode, newSonNodeCost);
                    parentsMap.put(sonNode, node);
                }
            }
            nodeMap.remove(node);
            node = this.findLowestNode(costMap);
            this.check(node);
        }
    }

    private String findLowestNode(Map<String, Integer> costMap) {
        int resultValue = BIGGEST;
        String resultKey = "";
        for (String key : costMap.keySet()) {
            int cost = costMap.get(key)==null ? 0 : costMap.get(key);
            if(cost < resultValue &&  nodeMap.containsKey(key)){
                resultValue = cost;
                resultKey = key;
            }
        }
        return resultKey;
    }

    private String generateShortestWay(String endName, String way){
        String parentName= parentsMap.get(endName);
        if(null==parentName || endName.equals("start")){
            return way = "start"+way;
        }else{
            way= "-"+endName +way;
            endName = parentName;
            return this.generateShortestWay(endName, way);
        }
    }

    public static void main(String[] args) {
        DijkstraAlgorithmPractiseA dijkstraAlgorithm = new DijkstraAlgorithmPractiseA();
        dijkstraAlgorithm.Dijkstra();
    }


}
