package com.feeyuk.algorithm.algorithmPicture;

import org.springframework.util.StringUtils;

import java.util.*;

public class BreadthFirstSearch {

/*    广度优先搜索可回答两类问题。
    第一类问题:从节点A出发，有前往节点B的路径吗?(在你的人 际关系网中，有芒果销售商吗?)
    第二类问题:从节点A出发，前往节点B的哪条路径最短?(哪个 芒果销售商与你的关系最近?)
    */

    private Map<String ,List<String>> friendsMap = new HashMap<>();

    private List<String> checkedPerson = new ArrayList<>();

//    graph["you"] = ["alice", "bob", "claire"]
//    graph["bob"] = ["anuj", "peggy"]
//    graph["alice"] = ["peggy"]
//    graph["claire"] = ["thom", "jonny"]
//    graph["anuj"] = []
//    graph["peggy"] = []
//    graph["thom"] = []
//    graph["jonny"] = []

    /*从you能否到fruit，且路径最短
        1。将you可以到的朋友加入队列
        2。朋友队列出列，检查每个朋友是否满足到fruit要求，是的话结束；否的话将朋友的朋友加入队列
            2。1 可以用递归解决
                base 满足要求,到fruit;或者队列为空
                recursion 朋友出列，将出列朋友的朋友添加到队列尾部
        说明：使用队列到话，可以查找最短路径；不使用队列的话，如使用无序列表，只能找到，不是最短
        使用队列保证了查找顺序按照每层进行
    * */

    public void prepareData(){
        friendsMap.put("you",Arrays.asList("alice", "bob", "claire"));
        friendsMap.put("bob",Arrays.asList("anuj", "peggy"));
        friendsMap.put("alice", Collections.singletonList("peggy"));
        friendsMap.put("claire",Arrays.asList("thom", "jonny"));
        friendsMap.put("anuj", Collections.singletonList(""));
        friendsMap.put("peggy", Collections.singletonList("end"));
        friendsMap.put("thom", Collections.singletonList(""));
        friendsMap.put("jonny", Collections.singletonList(""));
    }

    public String checkNearFruitVendor(String name){
        return null;
    }

    public String checkFruitVendor(String name){
        //生成数据
        this.prepareData();
        Queue<String> friendQueue = new LinkedList<>();

        this.putFriendsToQueueByName(friendQueue, name);

        System.out.println(this.checkFriend(friendQueue));

        return null;
    }

    public boolean checkFriend(Queue<String> friendQueue){
        System.out.println("friendQueue="+friendQueue);
        String name = friendQueue.poll();
        boolean vendorResult = checkFruitVendorByName(name);
        if(friendQueue.isEmpty() ||vendorResult){
            if(vendorResult){
                return true;
            }else{
                return false;
            }
        }else{
            if(!checkedPerson.contains(name)){
                checkedPerson.add(name);
            }
            this.putFriendsToQueueByName(friendQueue,name);
            return this.checkFriend(friendQueue);

        }
    }

    private boolean checkFruitVendorByName(String friendName) {
        if(StringUtils.isEmpty(friendName)){
            return false;
        }
        boolean result = friendName.endsWith("end");
        if(result){
            System.out.println("success find==="+ friendName);
            Stack<String> descWay = this.generateFriendWay(friendName, new Stack<>());
            int size = descWay.size();
            for (int i = 0; i<size; i++) {
                System.out.println(descWay.pop());
            }
        }
        return result;
    }


    private Stack<String> generateFriendWay(String friendName, Stack<String> friendWayList) {
        String parent;
        friendWayList.add(friendName);
        if(friendName.equals("you")){

            return friendWayList;
        }  else {
            parent = friendWay.get(friendName);
            friendName = parent;
            return this.generateFriendWay(friendName, friendWayList);
        }
    }


    Map<String,String> friendWay = new HashMap<>();

    private void putFriendsToQueueByName(Queue<String> friendQueue, String name) {
        List<String> youFriendList = friendsMap.get(name);
        if(StringUtils.isEmpty(name)){
            return ;
        }
        for (String friend : youFriendList) {
            friendQueue.add(friend);
            friendWay.put(friend, name);
        }

//        if(!CollectionUtils.isEmpty(youFriendList)){
//            friendQueue.addAll(youFriendList);
//
//        }
    }


    public static void main(String[] args) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.checkFruitVendor("you");
    }

}
