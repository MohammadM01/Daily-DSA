class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        int[][] dp = new int[n + 2][n + 2];
        arr[0] = arr[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        return solve(1, n, arr, dp);
    }

    int solve(int start, int end, int[] arr, int[][] dp) {
        int maxCoins = 0;
        int coins = 0;

        if (start > end) return 0;
        if (dp[start][end] != 0) return dp[start][end];

        for (int last = start; last <= end; last++) {
            coins = arr[start - 1] * arr[last] * arr[end + 1]
                  + solve(start, last - 1, arr, dp)
                  + solve(last + 1, end, arr, dp);

            maxCoins = Math.max(maxCoins, coins);
        }

        return dp[start][end] = maxCoins;
    }
}