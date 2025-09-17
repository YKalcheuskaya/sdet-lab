package leetcode;

public class FirstUniqueChar {
    public boolean firstUniqueChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            if (count[c - 'a'] == 1) {
                return true;
            }
        }
        return false;
    }
}
