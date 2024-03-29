package com.nt.binary.search;

/**
 * @author Enzo
 * @date : 2023/6/27
 */
public class BinarySearch {
    public static void main(String[] args) {

        int key = 3;
        int[] arr = {1, 2, 3, 5, 7, 8, 9, 10};

        System.out.println(binarySearch(arr, key));

        System.out.println(binarySearch(arr, key, 0, arr.length - 1));


    }

    public static int binarySearch(int[] a, int key) {

        // 定义初始查找范围
        int low = 0;
        int high = a.length - 1;

        if (key < a[low] && key > a[high]) {
            return -1;
        }

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归实现二分查找
     *
     * @param a
     * @param key
     * @return
     */
    public static int binarySearch(int[] a, int key, int formIndex, int toIndex) {

        // 基本判断 当起始位置大于结束位置时,直接返回-1特殊情况超出最大最小值直接返回01
        if (key < a[formIndex] || key > a[toIndex] || formIndex > toIndex) {
            return -1;
        }
        // 计算中间位置
         int mid = (formIndex + toIndex) / 2;

        // 判断中间位置和key的大小关系,更改搜索范围,递归调用
        if (a[mid] < key) {
            return binarySearch(a, key, mid + 1, toIndex);
        } else if (a[mid] > key) {
            return binarySearch(a, key, formIndex, mid - 1);
        } else {
            return mid;
        }
    }



}
