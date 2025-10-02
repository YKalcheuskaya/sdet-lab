package leetcode;

import java.util.*;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), 0, 0, n, res);
        return res;
    }

    private static void dfs(StringBuilder path, int open, int close, int n, List<String> res) {
        if (path.length() == 2 * n) { // все скобки расставлены
            res.add(path.toString());
            return;
        }
        if (open < n) {               // можем добавить "("
            path.append('(');
            dfs(path, open + 1, close, n, res);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
        if (close < open) {           // можем добавить ")"
            path.append(')');
            dfs(path, open, close + 1, n, res);
            path.deleteCharAt(path.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        // -> ["((()))","(()())","(())()","()(())","()()()"]
    }
}

