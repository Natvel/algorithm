package com.nt.sliding.window;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Enzo
 * @date : 2024/1/15
 */
public class FindAllAnagarms {


    /**
     * 暴力法 枚举所有长度为p.length()的子串
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s,String p){

        // 定义一个结果列表
        List< Integer> result = Lists.newArrayList();

        // 1.统计p中所有字符频次
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }
        // 2.遍历s 以每一个字符作为起始考察长度为p.length()的子串
        for (int i = 0; i < s.length() - p.length(); i++) {
           // 3.判断当前子串是否为p的字母异位词
            boolean isMatched = true;

            // 定义一个数组 统计子串中所有字符频次
            int[] subCharCounts = new int[26];

            for (int j = i; j < i + p.length(); j++) {
                subCharCounts[s.charAt(j) - 'a']++;
                if (subCharCounts[s.charAt(j) - 'a'] > pCharCounts[s.charAt(j) - 'a']) {
                    isMatched = false;
                    break;
                }
            }
            if (isMatched){
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 方法二 滑动窗口发 分别移动起始和结束位置
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s,String p){


        // 定义一个结果列表
        List< Integer> result = Lists.newArrayList();

        // 1.统计p中所有字符频次
        int[] pCharCounts = new int[26];

        for (int i = 0; i < p.length(); i++) {
            pCharCounts[p.charAt(i) - 'a']++;
        }
        // 统计子串所有字符出现频次的数组
       int[] subStrChatCounts =  new int[26];

        // 定义双指针 指向窗口的起始和结束位置
        int start = 0,end = 1;
        // 2. 移动指针 总是截取字符串出现频次全部小于p中字符频次子串
        while (end <= s.length()){
            // 当前新增字符
            char newChar = s.charAt(end - 1);

            subStrChatCounts[newChar - 'a']++;
            // 3. 判断当前子串是否符合要求
            while (subStrChatCounts[newChar - 'a']  > pCharCounts[newChar - 'a'] && start < end){
                char removeChar = s.charAt(start);
                subStrChatCounts[removeChar - 'a']--;
                start++;
            }
            // 如果当前子串长度等于p的长度 name就是一个字母异位词
            if (end - start == p.length()){
                result.add(start);
            }
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        FindAllAnagarms anagarms = new FindAllAnagarms();
        anagarms.findAnagrams2(s, p).forEach((System.out::println));
    }


}
