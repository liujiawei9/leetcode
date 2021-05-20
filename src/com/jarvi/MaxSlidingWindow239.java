package com.jarvi;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4<= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSlidingWindow239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 窗口个数
        int[] result = new int[nums.length - k + 1];
        //单调队列，存储窗口最大值
        Deque<Integer> queue = new LinkedList<>();
        // 遍历数组中元素，right表示滑动窗口右边界
        for (int right = 0; right < nums.length; right++) {
            // 如果队列不为空且当前考察元素大于等于队尾元素，则将队尾元素移除。
            // 直到，队列为空或当前考察元素小于新的队尾元素
            while (!queue.isEmpty() && nums[right] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            // 存储元素下标
            queue.addLast(right);

            // 计算窗口左侧边界
            int left = right - k + 1;
            // 当队首元素的下标小于滑动窗口左侧边界left时，表示队首元素已经不再滑动窗口内，因此将其从队首移除
            if (queue.peekFirst() < left) {
                queue.removeFirst();
            }

            // 由于数组下标从0开始，因此当窗口右边界right+1大于等于窗口大小k时，意味着窗口形成。
            // 此时，队首元素就是该窗口内的最大值
            if (right + 1 >= k) {
                result[left] = nums[queue.peekFirst()];
            }
        }
        return result;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int length = nums.length;
        int[] res = new int[length - k + 1];
        // 三个指针
        int left = 0;
        int right = k - 1;
        int maxIndex = getMaxIndex(nums, 0, k - 1);
        while (true) {
            // 如果最大值所在的索引在窗口的范围内
            if (maxIndex >= left) {
                res[left++] = nums[maxIndex];
                right++; // 更新滑动窗口
                if (right == length) { // 数组遍历完毕，退出
                    break;
                }
                // 如果滑动窗口新增加的数字最大，那么更新
                if (nums[right] >= nums[maxIndex]) {
                    maxIndex = right;
                }
            } else {
                // 如果最大值所在索引不在窗口范围内，那么需要重新找到索引
                // 特殊处理数组为单调递减的情况
                if (nums[left] >= nums[maxIndex] - 1) {
                    maxIndex = left;
                } else {
                    maxIndex = getMaxIndex(nums, left, right);
                }
            }
        }
        return res;
    }

    /**
     * 找出数组指定范围内最大值所在的索引
     */
    public int getMaxIndex(int[] nums, int start, int end) {
        int index = start;
        int val = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (val <= nums[i]) {
                val = nums[i];
                index = i;
            }
        }
        return index;
    }

}
