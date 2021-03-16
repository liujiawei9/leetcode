package com.jarvi;

/**
 * leetcode test 53
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray53 {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{1}));
    }


    /**
     * 贪心算法
     * @param nums
     * @return
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int maxSubArray2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            //currSum有增益效果，则保留：currSum = nums[i] + currSum
            //currSum无增益效果，则抛弃，从当前元素重新累加：currSum = nums[i]
            currSum = Math.max(nums[i], nums[i] + currSum);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    /**
     * 暴力法
     * 效率太低
     * 时间复杂度 O(n³)
     * 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        // 计算长度为i的子数组的和
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j + i <= nums.length; j++) {
                int count = 0;
                int sum = 0;
                while (count < i) {
                    sum += nums[j + count];
                    count++;
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

}
