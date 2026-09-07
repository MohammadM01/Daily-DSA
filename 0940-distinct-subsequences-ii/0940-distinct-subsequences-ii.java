class Solution {
    public int distinctSubseqII(String s) {
        long mod = 1000000007L;
        long dp = 1;
        long[] last = new long[26];
        for (char ch : s.toCharArray()) {
            int c = ch - 'a';
            long old = dp;
            dp = (2 * dp - last[c] + mod) % mod;
            last[c] = old;
        }
        return (int) ((dp - 1 + mod) % mod);
    }
}