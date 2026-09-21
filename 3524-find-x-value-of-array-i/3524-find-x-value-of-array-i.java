class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        for (int i = 0; i < nums.length; i++) {
            long[] next = new long[k];
            int x = nums[i] % k;
            next[x]++;
            for (int j = 0; j < k; j++) {
                int r = (j * x) % k;
                next[r] += dp[j];
            }
            dp = next;
            for (int j = 0; j < k; j++)
                ans[j] += dp[j];
        }
        return ans;
    }
}