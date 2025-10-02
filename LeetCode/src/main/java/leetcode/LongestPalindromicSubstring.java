package leetcode;

// EXPAND AROUND CENTER

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);       // odd length
            int len2 = expandAroundCenter(s, i, i + 1);   // even length
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // length of the palindrome
    }
}

// public int countSubstrings(String s) {
//    int n = s.length(), ans = 0;
//    for (int i = 0; i < n; i++) {
//        ans += expandCount(s, i, i);     // нечётные
//        ans += expandCount(s, i, i + 1); // чётные
//    }
//    return ans;
//}
//private int expandCount(String s, int l, int r) {
//    int cnt = 0;
//    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
//        cnt++; l--; r++;
//    }
//    return cnt;
//}
