package com.jarvi;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * <p>
 * 输出: [1,3,12,0,0]
 * <p>
 * 说明: 必须在原数组上操作，不能拷贝额外的数组。尽量减少操作次数。
 * <p>
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class MoveZeroes283 {

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums2 = {0, 0, 0, 3, 12};
        int[] nums3 = {0, 0, 0, 3, 12, 0};
        int[] nums4 = {0, 1, 1, 0, 2, 12, 1};
        int[] nums5 = {1, 0, 92, 7, 0, 0, 3, 4};

        new MoveZeroes283().moveZeroes1(nums1);
        new MoveZeroes283().moveZeroes1(nums2);
        new MoveZeroes283().moveZeroes1(nums3);
        new MoveZeroes283().moveZeroes1(nums4);
        new MoveZeroes283().moveZeroes1(nums5);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(nums3));
        System.out.println(Arrays.toString(nums4));
        System.out.println(Arrays.toString(nums5));
    }


    /**
     * 存在4种情况：
     * <p>
     * 情况 1：nums[i] != 0 && nums[j] != 0
     * 只需要 i++ j++
     * <p>
     * 情况 2：nums[i] == 0 && nums[j] != 0
     * 该情况不可能出现
     * <p>
     * 情况 3：nums[i] != 0 && nums[j] == 0
     * 交换位置
     * nums[j++] = nums[i];
     * nums[i] = 0;
     * <p>
     * 情况 4：nums[i] == 0 && nums[j] == 0
     * 只需要 i++，j不变
     * <p>
     * 时间复杂度O(N)
     * 空间复杂度O(1)
     *
     * @param nums 输入数组
     */
    public void moveZeroes1(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && nums[j] != 0) {
                j++;
                continue;
            }
            if (nums[i] != 0 && nums[j] == 0) {
                //交换位置
                nums[j++] = nums[i];
                nums[i] = 0;
            }
        }
    }

    /**
     * 冒泡法，将 0 往数组最后进行移动。效率低
     * 时间复杂度O(N^2)
     * 空间复杂度O(1)
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
    }

}
