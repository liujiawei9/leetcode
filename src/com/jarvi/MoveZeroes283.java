package com.jarvi;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeroes283 {

    /**
     * 方法一：冒泡法，将 0 往数组最后进行移动。效率低
     * 时间复杂度O(N^2)
     */
    public void moveZeroes(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < nums.length - 1; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        new MoveZeroes283().moveZeroes(new int[]{0, 1, 0, 3, 12});
        new MoveZeroes283().moveZeroes(new int[]{0, 0, 0, 3, 12});
        new MoveZeroes283().moveZeroes(new int[]{0, 0, 0, 3, 12, 0});
        new MoveZeroes283().moveZeroes(new int[]{5, 0, 0, 3, 12, 0});
        new MoveZeroes283().moveZeroes(new int[]{1, 0, 92, 7, 0, 0, 3, 4});
    }

}
