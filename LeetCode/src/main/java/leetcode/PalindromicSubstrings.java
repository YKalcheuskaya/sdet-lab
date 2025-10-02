package leetcode;

// EXPAND AROUND CENTER

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            ans += expandCount(s, i, i);     // odd length
            ans += expandCount(s, i, i + 1); // even length
        }
        return ans;
    }

    private int expandCount(String s, int l, int r) {
        int cnt = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            cnt++;
            l--;
            r++;
        }
        return cnt;
    }
}
