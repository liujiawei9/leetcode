package com.jarvi;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob198 {

    public static void main(String[] args) {
        System.out.println(rob3(new int[]{1, 2, 3, 1}));
        System.out.println(rob3(new int[]{2, 1, 1, 2}));
        System.out.println(rob3(new int[]{2, 7, 9, 3, 1}));
    }

    /**
     * 动态规划
     * dp[n][2]，每间有两种情况。
     * 状态转移：
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][2];
        //初始情况
        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    /**
     * 动态规划，优化空间复杂度版本。因为只需要记住上一阶段的结果，所以 dp table可优化。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        //记录上一阶段结果
        //前一间屋rob
        int rob = nums[0];
        //前一间屋未rob
        int unrob = 0;
        for (int i = 1; i < nums.length; i++) {
            //当前未rob,前一间可能rob，也可能未rob
            int temp = Math.max(unrob, rob);
            //当前rob只能由前一间未rob转移过来
            rob = unrob + nums[i];
            unrob = temp;
        }
        return Math.max(rob, unrob);
    }

    /**
     * 代码优化版
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return
     */
    public static int rob3(int[] nums) {
        int preMaxRobed = 0;
        int preMaxUnRob = 0;
        for (int num : nums) {
            int temp = Math.max(preMaxRobed, preMaxUnRob);
            preMaxRobed = preMaxUnRob + num;
            preMaxUnRob = temp;
        }
        return Math.max(preMaxRobed, preMaxUnRob);
    }


    /**
     * leetcode官方解答版本
     * 考虑所有可能的抢劫方案过于困难。一个自然而然的想法是首先从最简单的情况开始。记：
     * <p>
     * f(k) = 从前 k 个房屋中能抢劫到的最大数额，Ai = 第 i 个房屋的钱数。
     * <p>
     * 首先看 n = 1 的情况，显然 f(1) = A1
     * 再看 n = 2，f(2) = max(A1, A2)。
     * <p>
     * 对于 n = 3，有两个选项:
     * <p>
     * 抢第三个房子，将数额与第一个房子相加。
     * <p>
     * 不抢第三个房子，保持现有最大数额。
     * <p>
     * 显然，你想选择数额更大的选项。于是，可以总结出公式：
     * <p>
     * f(k) = max(f(k – 2) + Ak, f(k – 1))
     * <p>
     * 我们选择 f(–1) = f(0) = 0 为初始情况，这将极大地简化代码。
     * <p>
     * 答案为 f(n)。可以用一个数组来存储并计算结果。不过由于每一步你只需要前两个最大值，两个变量就足够用了。
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static int rob4(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }
}
