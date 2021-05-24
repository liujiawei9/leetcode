package com.jarvi;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TrappingRainWater42 {

    /**
     * 按列累加 （暴力法）
     *
     * 每列可以容纳的水量 = Math.min(左边最高柱子高度，右边最高柱子高度) - 当前列的高度  （注：两者之差大于 0 时有效）
     * 总储水量就等于每列累加之和。
     *
     * 时间复杂度：O(n²），遍历每一列需要 n 次，找出左边最高和右边最高的柱子加起来也是 n 次，所以是 n²。
     * 空间复杂度：O(1）。没有使用额外空间
     */
    public int trap(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不能储水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            //找出左边柱子最大高度
            int leftMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            //找出右边柱子最大高度
            int rightMax = 0;
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            //累加储水量
            sum += Math.min(leftMax, rightMax) - height[i];
        }
        return sum;
    }

    /**
     * dp
     * maxLeft[i] = max(maxLeft[i-1], height[i-1])
     *
     */
    public int trap2(int[] height) {

    }



}
