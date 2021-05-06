package com.jarvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d，使得 a + b + c + d 的值与 target 相等。
 * 找出所有满足条件且不重复的四元组。
 *
 * 注意：答案中不可以包含重复的四元组。
 *
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * 示例 2：
 * 输入：nums = [], target = 0
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n)。快排的空间复杂度为 O(logn) 。而我们修改了输入的数组，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(n)。
 */
public class FourSum18 {

    public static void main(String[] args) {
        //[[-4,0,1,2],[-1,-1,0,1]]
        System.out.println(fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1));
        //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
        // [[-5,-4,-3,1]]
        System.out.println(fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return results;
        }
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            if (nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                break;
            }
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            if (nums[a] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (nums[a] + nums[b] + nums[b + 1] + nums[b + 2] > target) {
                    break;
                }
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                if (nums[a] + nums[b] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        results.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[++c]) ;
                        while (c < d && nums[d] == nums[--d]) ;
                    } else if (sum > target) {
                        while (c < d && nums[d] == nums[--d]) ;
                    } else {
                        while (c < d && nums[c] == nums[++c]) ;
                    }
                }
            }
        }
        return results;
    }


}
