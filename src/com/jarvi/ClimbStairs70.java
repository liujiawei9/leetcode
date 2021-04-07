package com.jarvi;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbStairs70 {


    public static void main(String[] args) {
        int n = 10;
        System.out.println("Stairs is " + n + ", result is " + new ClimbStairs70().climbStairs2(n));
    }

    /**
     * 斐波那契数列：
     * 0、1、1、2、3、5、8、13、21、34
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //把数列中相邻的三个数定义为a,b,c
        int a = 1;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public int climbStairs2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 动态规划
     * n = 0 , result = 0
     * n = 1 , result = 1
     * n = 2 , result = 2
     * n = 3 , result = 3
     * 到达n级，需要从n-1级或者n-2级转移过来。
     * 因此到达n级的方法等于到达n-1级和n-2级之和。
     * 状态转移方程：f(n) = f(n-1) + f(n-2)
     * @param n 台阶数
     * @return result 方法数
     */
    public int dp(int n) {
        //需要记录上两级的结果。
        int[] dp = new int[2];
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int result = 0;
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i <= n; i++) {
            result = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = result;
        }
        return result;
    }

}
