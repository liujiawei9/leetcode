package com.jarvi;

/**
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 *
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 *
 * 输入：height = [1,2,1]
 * 输出：2
 * 
 *
 * 提示：
 *
 * n = height.length
 * 2 <= n <= 3 * 10^4
 * 0 <= height[i] <= 3 * 10^4

 */
public class MaxArea11 {

    public static void main(String[] args) {
        //49
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        //1
        System.out.println(maxArea(new int[]{1, 1}));
        //16
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        //2
        System.out.println(maxArea(new int[]{1, 2, 1}));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        while (i < j) {
            maxArea = height[i] < height[j] ?
                    Math.max(maxArea, (j - i) * height[i++]) : Math.max(maxArea, (j - i) * height[j--]);
        }
        return maxArea;
    }
}
