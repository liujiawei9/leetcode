package com.jarvi;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * 案例 1:
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 9
 * <p>
 * 输出: True
 * <p>
 * <p>
 * 案例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 28
 * <p>
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 时间复杂度：O(n)，其中 n 是节点的数量。最坏的情况下，整棵树被遍历一次。
 *
 * 空间复杂度：O(n)。最坏的情况下，set 存储 n 个节点的值。
 *

 */
public class TwoSumBST653 {

    public static void main(String[] args) {
    }

    public static boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return find(set, root, k);
    }

    private static boolean find(Set<Integer> set, TreeNode node, int k) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        } else {
            set.add(node.val);
            return find(set, node.left, k) || find(set, node.right, k);
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}