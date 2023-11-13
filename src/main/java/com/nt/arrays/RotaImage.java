package com.nt.arrays;

/**
 * @author Enzo
 * @date : 2023/6/27
 */
public class RotaImage {
    // 方法一：数学方法(矩阵准换,在翻转每一行)
    public void rotate(int[][] matrix) {

        int length = matrix.length;
        // 1. 转置矩阵
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 2. 翻转每一行
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - j - 1];
                matrix[i][length - j - 1] = temp;
            }
        }
    }

    /**
     * 分治思想,分为四个子矩阵分别考虑
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2 + length % 2; i++) {
            for (int j = 0; j < length / 2; j++) {

                // 对于matrix[i][j]，
                // 定义一个临时数组,保存对应的四个元素
                int[] temp = new int[4];

                int row = i;
                int col = j;
                // 行列转换的规律 row + newCol -> length - 1,col = newRow

                for (int k = 0; k < temp.length; k++) {
                    temp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = length - 1 - x;
                }
                // 再次遍历要处理的四个位置,将旋转之后的数据填入
                for (int k = 0; k < temp.length; k++) {
                    // 用上一个值替换当前的值
                    matrix[row][col] = temp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = length - 1 - x;
                }
            }
        }
    }


    /**
     * 分治思想,分为四个子矩阵分别考虑
     *
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            for (int j = 0; j < length / 2; j++) {

                int temp = matrix[i][j];

                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = temp;

            }

        }
    }

    public static void main(String[] args) {

        int[][] image1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] image2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        RotaImage rotaImage = new RotaImage();
        rotaImage.rotate3(image1);
        rotaImage.printImage(image1);
        rotaImage.rotate3(image2);
        rotaImage.printImage(image2);


    }


    private void printImage(int[][] image) {
        System.out.println("image");
        for (int[] ints : image) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

}
