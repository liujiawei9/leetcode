package com.jarvi;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class MaxProfit121 {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    }


    /**
     * 动态规划
     * <p>
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/bao-li-mei-ju-dong-tai-gui-hua-chai-fen-si-xiang-b/
     * 时间复杂度：O(N)O(N)；
     * 空间复杂度：O(N)O(N)。
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int length = prices.length;
        if (length <= 1) {
            return 0;
        }
        int[][] dp = new int[length][2];
        //0 表示未持有股票
        dp[0][0] = 0;
        //1 表示持有股票
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        printDPTable(dp);
        return dp[length - 1][0];
    }


    /**
     * 动态规划
     * 利用 & 1 对数组进行循环利用。
     * 由于 dp[i] 仅仅依赖于 dp[i - 1] ，因此，我们可以使用滚动数组的技巧压缩变量。下面根据“maxProfit2”进行修改：
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }
        int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i & 1][0] = Math.max(dp[(i - 1) & 1][0], dp[(i - 1) & 1][1] + prices[i]);
            dp[i & 1][1] = Math.max(dp[(i - 1) & 1][1], -prices[i]);
        }
        return dp[(len - 1) & 1][0];
    }

    private static void printDPTable(int[][] dp) {
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt+ "    ");
            }
            System.out.println();
        }
    }

    /**
     * 只有在最低价时买入，才可以获取最大利润。
     * 时间复杂度 O(n)
     * 空间复杂度：O(1)
     *
     * @param prices 每日价格
     * @return 最大利润
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                //找到最低价格，买入
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                //找到最低价格后的最高价格，卖出
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }


}
