package com.jarvi;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TopKFrequentElements347 {

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }

    /**
     * 小顶堆
     * 复杂度分析：
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //arr[0] 存放元素值，arr[1]存放元素统计次数。
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        map.forEach((key, value) -> {
            if (queue.size() < k) {
                queue.offer(new int[]{key, value});
            } else if (queue.peek()[1] < value) {
                queue.poll();
                queue.offer(new int[]{key, value});
            }
        });

        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }


}
