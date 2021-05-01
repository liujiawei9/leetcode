package com.jarvi;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum167 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        //two sum
        System.out.println(Arrays.toString(twoSum(nums, 4)));
        System.out.println(Arrays.toString(twoSum1(nums, 4)));
        System.out.println(Arrays.toString(twoSum2(nums, 4)));
        System.out.println("========================================");
        System.out.println(Arrays.toString(twoSum(nums, 9)));
        System.out.println(Arrays.toString(twoSum1(nums, 9)));
        System.out.println(Arrays.toString(twoSum2(nums, 9)));
        System.out.println("========================================");
        System.out.println(Arrays.toString(twoSum(nums, 30)));
        System.out.println(Arrays.toString(twoSum1(nums, 30)));
        System.out.println(Arrays.toString(twoSum2(nums, 30)));
    }

    /**
     * 双指针 + 二分查找
     * 时间复杂度：
     * 空间复杂度：
     */
    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (numbers[i] + numbers[m] > target) {
                j = m - 1;
                continue;
            }
            if (numbers[m] + numbers[j] < target) {
                i = m + 1;
                continue;
            }
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];
    }

    /**
     * 二分查找法
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public static int[] twoSum1(int[] numbers, int target) {
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            int low = i + 1;
            int high = length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                }
                if (numbers[mid] < target - numbers[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return new int[0];
    }

    /**
     * 双指针法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[0];
    }

    /**
     * 二分查找，返回目标值下标
     */
    public static int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
