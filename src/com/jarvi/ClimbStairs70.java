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
        System.out.println("-5 : " + climbStairs(-5));//0
        System.out.println("0 : " + climbStairs(0));//0
        System.out.println("1 : " + climbStairs(1));//1
        System.out.println("2 : " + climbStairs(2));//2
        System.out.println("3 : " + climbStairs(3));//3
        System.out.println("4 : " + climbStairs(4));//5
        System.out.println("5 : " + climbStairs(5));//8
        System.out.println("6 : " + climbStairs(6));//13
        System.out.println("7 : " + climbStairs(7));//21
        System.out.println("8 : " + climbStairs(8));//34
        System.out.println("9 : " + climbStairs(9));//55
        System.out.println("10 : " + climbStairs(10));// 89
        System.out.println("11 : " + climbStairs(11));// 144
    }

    /**
     * 斐波那契数列：
     * 0、1、1、2、3、5、8、13、21、34
     */
    public static int climbStairs(int n) {
        if (n <= 0) {
            return 0;
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

    /**
     * 递归
     */
    public static int climbStairs2(int n) {
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
     * 0,1,1,2,3,5,8,13
     * n = 0 , result = 0
     * n = 1 , result = 1
     * n = 2 , result = 2
     * n = 3 , result = 3
     * n = 4 , result = 5
     * n = 5 , result = 8
     * n = 6 , result = 13
     * 到达n级，需要从n-1级或者n-2级转移过来。
     * 因此到达n级的方法等于到达n-1级和n-2级之和。
     * 状态转移方程：f(n) = f(n-1) + f(n-2)
     * @param n 台阶数
     * @return result 方法数
     */
    public static int dp(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = {1,1};
        for (int i = 2; i <= n; i++) {
            int temp = dp[1];
            dp[1] = dp[0] + dp[1];
            dp[0] = temp;
        }
        return dp[1];
    }

}
