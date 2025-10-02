package leetcode;

import java.util.*;
public class PermuteAll {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        dfs(nums, used, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == nums.length) { res.add(new ArrayList<>(cur)); return; }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true; cur.add(nums[i]);
            dfs(nums, used, cur, res);
            cur.remove(cur.size()-1); used[i] = false;
        }
    }
    public static void main(String[] a){ System.out.println(new PermuteAll().permute(new int[]{1,2,3})); }
}

