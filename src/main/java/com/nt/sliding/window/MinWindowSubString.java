package com.nt.sliding.window;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Enzo
 * @date : 2024/1/11
 */
public class MinWindowSubString {

    /**
     * 方法一 暴力法
     * 枚举s中所有子串
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s,String t){
        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";
        // 定义一个hashmap,保存t中字符出现的频次
        Map<Character,Integer> hashMap = Maps.newHashMap();
        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = hashMap.getOrDefault(c, 0);
            hashMap.put(c, count + 1);
        }

        // 接下来在s中搜索覆盖子串 遍历所有字符 作为当前子串的起始位置
        for (int i = 0; i < s.length(); i++) {
            // 遍历i之后不小于t长度的位置 作为子串的结束位置
            for (int j = i+t.length(); j <= s.length() ; j++) {
                // 定义一个hashmap,保存t中字符出现的频次
                Map<Character,Integer> subStrCharFrequency = Maps.newHashMap();
                for (int k = i; k < j; k++) {
                    char c = s.charAt(k);
                    Integer count = subStrCharFrequency.getOrDefault(c, 0);
                    subStrCharFrequency.put(c, count + 1);
                }
                // 如果当前子串符合覆盖子串的要求 并且比之前的最小子串要短就替换
                if (check(hashMap, subStrCharFrequency) && ("".equals(minSubString) || j - i < minSubString.length())) {
                    minSubString = s.substring(i, j);
                }
            }
        }
        return minSubString;
    }



    // 提炼一个方法 用于检查当前子串是否覆盖t的子串
    public boolean check(Map<Character, Integer> tFreq,Map<Character,Integer> substrFre){
        // 遍历t中每个字符的频次 与subStr进行比较
        for (Character character : tFreq.keySet()) {
            if (substrFre.getOrDefault(character,0)< tFreq.get(character)){
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二
     * 滑动窗口优化
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s,String t){

        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";
        // 定义一个hashmap,保存t中字符出现的频次
        Map<Character,Integer> hashMap = Maps.newHashMap();
        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = hashMap.getOrDefault(c, 0);
            hashMap.put(c, count + 1);
        }

        // 定义左右指针 指向滑动窗口的起始和结束位置

        int start = 0, end = t.length();
        while (end <= s.length()) {
            // 定义一个hashmap,保存t中字符出现的频次
            Map<Character,Integer> subStrCharFrequency = Maps.newHashMap();
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                Integer count = subStrCharFrequency.getOrDefault(c, 0);
                subStrCharFrequency.put(c, count + 1);
            }
            // 如果当前子串符合覆盖子串的要求 并且比之前的最小子串要短就替换
            if (check(hashMap, subStrCharFrequency)) {
                if ("".equals(minSubString) || end - start < minSubString.length()){
                    minSubString = s.substring(start, end);
                }
                // 只要是覆盖子串 就移动初始位置 缩小窗口 寻找当前的局部最优解
                start++;
            }else {
                // 如果不是覆盖子串 需要扩大窗口继续寻找新的子串
                end++;
            }
        }
        return minSubString;
    }


    public String minWindow3(String s,String t) {

        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";
        // 定义一个hashmap,保存t中字符出现的频次
        Map<Character, Integer> hashMap = Maps.newHashMap();
        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = hashMap.getOrDefault(c, 0);
            hashMap.put(c, count + 1);
        }
        int start = 0, end = t.length();
        Map<Character,Integer> subStrCharFrequency = Maps.newHashMap();

        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);
            // 新增字符频次加1
            if (hashMap.containsKey(newChar)){
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);
            }
            // 如果当前子串符合覆盖子串的要求 并且比之前的最小子串要短就替换
            while (check(hashMap, subStrCharFrequency) && start < end) {
                if ("".equals(minSubString) || end - start < minSubString.length()){
                    minSubString = s.substring(start, end);
                }
                // 对要删除的字符 频次减一

                char removeChar = s.charAt(start);
                if (hashMap.containsKey(removeChar)){
                    subStrCharFrequency.put(removeChar, subStrCharFrequency.getOrDefault(removeChar, 0) - 1);
                }
                // 只要是覆盖子串 就移动初始位置 缩小窗口 寻找当前的局部最优解
                start++;
            }
            end++;
        }
        return minSubString;
    }


    /**
     * 方法四
     * @param s
     * @param t
     * @return
     */
    public String minWindow4(String s,String t) {

        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";
        // 定义一个hashmap,保存t中字符出现的频次
        Map<Character, Integer> hashMap = Maps.newHashMap();
        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer count = hashMap.getOrDefault(c, 0);
            hashMap.put(c, count + 1);
        }
        int start = 0, end = 1;
        Map<Character,Integer> subStrCharFrequency = Maps.newHashMap();

        // 定义一个子串贡献值 统计t中的
        int charCount = 0;
        while (end <= s.length()) {
            char newChar = s.charAt(end - 1);
            // 新增字符频次加1
            if (hashMap.containsKey(newChar)){
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);
                // 如果子串中频次小于t中频次当前字符有贡献
                if (subStrCharFrequency.get(newChar) <= hashMap.get(newChar)){
                    charCount++;
                }
            }
            // 如果当前子串符合覆盖子串的要求 并且比之前的最小子串要短就替换
            while (charCount == t.length() && start < end) {
                if ("".equals(minSubString) || end - start < minSubString.length()){
                    minSubString = s.substring(start, end);
                }
                // 对要删除的字符 频次减一

                char removeChar = s.charAt(start);
                if (hashMap.containsKey(removeChar)){
                    subStrCharFrequency.put(removeChar, subStrCharFrequency.getOrDefault(removeChar, 0) - 1);
                    //  如果子串中的频次不够t中频次 贡献值减少
                    if (subStrCharFrequency.get(removeChar) < hashMap.get(removeChar)){
                        charCount--;
                    }
                }
                // 只要是覆盖子串 就移动初始位置 缩小窗口 寻找当前的局部最优解
                start++;
            }
            end++;
        }
        return minSubString;
    }




    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";

        MinWindowSubString subString = new MinWindowSubString();
        System.out.println(subString.minWindow4(s, t));
    }
}
