class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int arr[] = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        int dp[][] = new int[n + 2][n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        return solve(1, n, arr, dp);
    }

    int solve(int i, int j, int arr[], int dp[][]) {
        int max = 0;
        if (i > j) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        for (int k = i; k <= j; k++) {
            int coins = arr[i - 1] * arr[k] * arr[j + 1]
                    + solve(i, k - 1, arr, dp)
                    + solve(k + 1, j, arr, dp);

            max = Math.max(max, coins);
        }
        return dp[i][j] = max;
    }
}