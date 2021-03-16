package com.jarvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens51 {

    private List<List<String>> solutions = new ArrayList<>();

    /**
     * 棋盘，下标代表行，值代表Queen所在列
     */
    private int[] board;

    /**
     * num of queens
     */
    private int n;

    public static void main(String[] args) {
        List<List<String>> s = new NQueens51().solveNQueens(8);
        System.out.println(s);
        System.out.println(s.size());
    }

    public List<List<String>> solveNQueens(int n) {
        if (n < 0 ) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        board = new int[this.n];
        Arrays.fill(board, -1);
        //从第0行开始
        solve(0);
        return solutions;
    }

    private void solve(int row) {
        //最后一行已放置
        if (row == n) {
            //构建结果，用于输出
            List<String> solution = new ArrayList<>(n);
            for (int column : board) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (column == i) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                solution.add(sb.toString());
            }
            solutions.add(solution);
            //clearBoard();
            return;
        }
        //棋盘每行有n列，所以棋子在每行可放置位置有n个
        for (int column = 0; column < n; column++) {
            //检查放置位置是否合法
            if (isValid(row, column)) {
                board[row] = column;
                solve(row + 1);
            }
        }
    }


    /**
     * 判断放置在当前位置是否合法
     *
     * @return true if the check result is valid
     */
    private boolean isValid(int row, int column) {

        int leftUp = column - 1;
        int rightUp = column + 1;

        //遍历前面已放置棋子的行，检查当前放置位置与前行放置位置是否存在冲突
        for (int i = row - 1; i >= 0; i--) {
            //检查棋子上方是否有其他棋子
            if (board[i] == column) {
                return false;
            }
            //检查左上对角线是否有其他棋子
            if (leftUp >= 0 && board[i] == leftUp) {
                return false;
            }
            //检查右上对角线是否有其他棋子
            if (rightUp <= n && board[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

}
