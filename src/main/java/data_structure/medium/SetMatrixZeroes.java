package data_structure.medium;

import java.util.*;

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        // 用来标记第0行和第0列是否有0
        boolean flag4Row = false;
        boolean flag4Col = false;

        // 遍历矩阵进行标记:
        // 如果某个元素[i,j]为0, 则把该元素在第0行与第0列的投影(即[0,j]与[i,0])标记为0
        // 另外, 如果该元素为第0行或第0列的元素, 还要把flag4Row或flag4Col标记为true
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                    if (i == 0) {flag4Row = true;}  // 如果第0行任意一个元素为0, 此值为true
                    if (j == 0) {flag4Col = true;}  // 如果第0列任意一个元素为0, 此值为true
                }
            }
        }

        // 对右下角 n-1 * n-1 矩阵进行赋0
        // 如果某个元素在第0行或第0列的投影为0, 则表示该元素应该赋值为0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 处理第0行
        if (flag4Row) {
            for(int j = 0; j < columns; j++) {
                matrix[0][j] = 0;
            }
        }

        // 处理第0列
        if (flag4Col) {
            for(int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] source_1 = new int[][]{
            { 1, 1, 1},
            { 1, 0, 1},
            { 1, 1, 1}
        };

        int[][] source_2 = new int[][]{
            { 0, 1, 2, 0},
            { 3, 4, 5, 2},
            { 1, 3, 1, 5}
        };

        SetMatrixZeroes zeroes = new SetMatrixZeroes();
        zeroes.setZeroes(source_1);
        zeroes.setZeroes(source_2);
        System.out.println(Arrays.deepToString(source_1).replace("], ", "]\n"));
        System.out.println(Arrays.deepToString(source_2).replace("], ", "]\n"));
    }
}
