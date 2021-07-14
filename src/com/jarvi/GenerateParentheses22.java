package com.jarvi;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * <p>
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * 提示：
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParentheses22 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    /**
     * DFS
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        dfs("", 0, 0, n, result);
        return result;
    }

    private static void dfs(String s, int left, int right, int n, List<String> results) {
        if (s.length() == 2 * n)  {
            results.add(s);
            return;
        }
        if (left < n) {
            dfs(s + "(", left + 1, right, n, results);
        }
        if (right < left) {
            dfs(s + ")", left, right + 1, n, results);
        }
    }

    /**
     * 回溯法
     */
    public static List<String> generateParenthesis1(int n) {
        List<String> results = new ArrayList<>();
        backtrack(new StringBuilder(), 0, 0, n, results);
        return results;
    }

    private static void backtrack(StringBuilder s, int left, int right, int n, List<String> results) {
        if (s.length() == n * 2) {
            results.add(s.toString());
            return;
        }
        if (left < n) {
            s.append('(');
            backtrack(s, left + 1, right, n, results);
            s.deleteCharAt(s.length() - 1);
        }
        if (right < left) {
            s.append(')');
            backtrack(s, left, right + 1, n, results);
            s.deleteCharAt(s.length() - 1);
        }
    }

    /**
     * 暴力法
     * <p>
     * 复杂度分析
     * 时间复杂度：O(2^(2n)n)，对于 2^(2n) 个序列中的每一个，我们用于建立和验证该序列的复杂度为 O(n)。
     * 空间复杂度：O(n)，除了答案数组之外，我们所需要的空间取决于递归栈的深度，每一层递归函数需要 O(1) 的空间，最多递归 2n 层，因此空间复杂度为 O(n)。
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) return result;
        generate(new char[2 * n], 0, result);
        return result;
    }

    private static void generate(char[] chars, int index, List<String> result) {
        if (chars.length == index) {
            if (valid(chars)) result.add(new String(chars));
        } else {
            chars[index] = '(';
            generate(chars, index + 1, result);
            chars[index] = ')';
            generate(chars, index + 1, result);
        }
    }

    private static boolean valid(char[] chars) {
        int count = 0;
        for (char c : chars) {
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

}
