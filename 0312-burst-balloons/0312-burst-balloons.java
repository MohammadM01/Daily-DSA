class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n + 2];
        int dp[][] = new int[n + 2][n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        for (int start = n; start >= 1; start--) {
            for (int end = start; end <= n; end++) {
                int maxCoins = 0;
                for (int last = start; last <= end; last++) {
                    int coins = arr[start - 1] * arr[last] * arr[end + 1]
                              + dp[start][last - 1]
                              + dp[last + 1][end];
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[start][end] = maxCoins;
            }
        }
        return dp[1][n];
    }
}