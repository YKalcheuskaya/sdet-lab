package leetcode;

import java.util.*;
public class SubsetsAll {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] nums, int i, List<Integer> cur, List<List<Integer>> res) {
        if (i == nums.length) { res.add(new ArrayList<>(cur)); return; }
        dfs(nums, i+1, cur, res);              // skip
        cur.add(nums[i]); dfs(nums, i+1, cur, res); cur.remove(cur.size()-1); // take
    }
    public static void main(String[] a){ System.out.println(new SubsetsAll().subsets(new int[]{1,2,3})); }
}
