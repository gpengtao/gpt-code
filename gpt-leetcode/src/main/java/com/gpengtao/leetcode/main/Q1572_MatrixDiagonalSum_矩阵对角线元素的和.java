package com.gpengtao.leetcode.main;

/**
 * <a href="https://leetcode.cn/problems/matrix-diagonal-sum/">...</a>
 *
 * @author pengtao.geng on 2023/8/11 10:26.
 */
public class Q1572_MatrixDiagonalSum_矩阵对角线元素的和 {

	public int diagonalSum(int[][] mat) {
		int sum = 0;
		for (int i = 0; i < mat.length; i++) {
			sum += mat[i][i];
			if (i != mat.length - 1 - i) {
				sum += mat[i][mat.length - 1 - i];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(new Q1572_MatrixDiagonalSum_矩阵对角线元素的和().diagonalSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
	}
}
