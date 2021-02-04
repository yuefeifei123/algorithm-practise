package com.feeyuk.algorithm.leetcode;

public class ReverseString {

    /*
    * 思路
    * 假设数组的长度为n，反转的话，在原始数组上进行操作的话，需要交换值；
    * 如果生成新数组，可以直接newChar[i]= originChar[length-1-i];
    * 在originChar上进行操作的话，交换originChar[i]与originChar[length-1-i]
    * 当i>=length-1-i时，交换完毕

    * */
    public void reverseString(char[] s) {
        char temp;
        for (int i = 0; i <= s.length-1-i; i++) {

            temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] =temp;
        }
    }

    public static void main(String[] args) {
        char[] test = {'h','e','l','l','o','f'};
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(test);
    }
}
