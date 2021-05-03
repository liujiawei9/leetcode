package com.jarvi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 若先将输入数组进行排序，则可以使用类似《167.两数之和 II - 输入有序数组》里用过的双指针解法
     * 三数之和其实和两数之和一样。a + b + c = 0 等价于 a + b = -c
     * 因此，只需要在第一层循环时，固定数值 target，然后寻找两个和等于 target 的数，即为答案之一。
     * 我们这里使用 i、j、k 三个指针，他们有以下关系：
     * i < j < k ;
     * nums[i] <= nums[j] <= nums[k]
     * 找到答案时：
     * nums[i] + nums[j] + nums[k]  == 0
     * <p>
     * 输入例子：[-1,0,1,2,-1,-4]
     * 排序后为：[-4,-1,-1,0,1,2]
     * 正确输出：[[-1,-1,2],[-1,0,1]]
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)。我们修改了输入的数组，在实际情况下不一定允许，因此也可以看成使用了一个额外的数组存储了 nums 的副本并进行排序，空间复杂度为 O(n)。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return results;
        }
        // 排序，可以排除重复项，利用首尾指针。这里的实现是快排，快排的时间复杂度为 O(n log(n))
        Arrays.sort(nums);
        // 第一层循环，固定目标值
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果 nums[i] > 0，则退出循环。因为 nums[i] 后面的数都比 nums[i] 大，因此不可能得到和为 0 的情况。
            if (nums[i] > 0) {
                break;
            }
            // 排除重复项
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 定义双指针
            int j = i + 1;
            int k = nums.length - 1;
            // 第二层循环
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // 如果 j、k 指针移动后指向的值与上一个相同，则要跳过，否则会得到重复项。
                    while (j < k && nums[j] == nums[++j]) ;
                    while (j < k && nums[k] == nums[--k]) ;
                } else if (sum > 0) {
                    while (j < k && nums[k] == nums[--k]) ;
                } else {
                    while (j < k && nums[j] == nums[++j]) ;
                }
            }
        }
        return results;
    }
}
