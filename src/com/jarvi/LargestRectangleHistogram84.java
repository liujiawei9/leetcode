package com.jarvi;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为[2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为10个单位。
 * <p>
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestRectangleHistogram84 {

    /**
     * 单调递增栈解法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 参考 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/84-by-ikaruga/
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int currIndex = stack.pop();
                int left = stack.peek();
                maxArea = Math.max(maxArea, (i - left - 1) * newHeights[currIndex]);
            }
            stack.push(i);
        }
        return maxArea;
    }


    /**
     * 暴力法（超时）
     * 时间复杂度：O(n^2)，这里 n 是输入数组的长度。
     * 空间复杂度：O(1)。
     */
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {

            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]) {
                left--;
            }

            int right = i;
            while (right < heights.length && heights[right + 1] >= heights[i]) {
                right++;
            }

            maxArea = Math.max(maxArea, (right - left + 1) * heights[i]);
        }
        return maxArea;
    }

}
