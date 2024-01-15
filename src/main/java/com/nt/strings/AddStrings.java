package com.nt.strings;

/**
 * @author Enzo
 * @date : 2023/7/13
 */
public class AddStrings {
    public String addString(String nums1, String nums2) {
        // 定义一个StringBuffer,保存最终的结果
        StringBuilder buffer = new StringBuilder();

        // 定义遍历两个字符串的初始位置
        int i = nums1.length() - 1;
        int j = nums2.length() - 1;

        // 用一个遍历保存当前的定位
        int carry = 0;
        // 从个位开始依次遍历所有位数,只要还有没有计算,就继续,其他位数补0
        while (i >= 0 || j >= 0 || carry != 0) {
            // 字符 要将ASCII码转换为数字
            int n1 = i >= 0 ? nums1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? nums2.charAt(j) - '0' : 0;
            // 对当前数位求和
            int sum = n1 + n2 + carry;
            // 把sum的个位保存到结果中,十位作为进位保存下来
            buffer.append(sum % 10);
            carry = sum / 10;
            // 移动指针,继续遍历下一轮
            i--;
            j--;
        }
        return buffer.reverse().toString();
    }

    public static void main(String[] args) {

        String nums1 = "56349123";
        String nums2 = "435542";
        AddStrings addStrings = new AddStrings();
        System.out.println(addStrings.addString(nums1, nums2));
    }
}
