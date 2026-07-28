class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        StringBuilder left = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) (i + 'a'));
            }
            if (freq[i] % 2 == 1) {
                mid.append((char) (i + 'a'));
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(mid);
        ans.append(left.reverse());
        return ans.toString();
    }
}