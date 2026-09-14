class Solution {
    HashMap<Integer, Integer> dp;

    public int rob(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        return fun(nums, n, 0, 1, dp);
    }

    public int fun(int a[], int n, int i, int free, int dp[][]) {
        if (i == n || i > n)
            return 0;
        if (dp[i][free] != -1)
            return dp[i][free];
        if (free == 0) {
            int a1 = fun(a, n, i + 1, 1, dp);
            dp[i][free] = a1;
            return a1;
        }
        int c1 = a[i] + fun(a, n, i + 1, 0, dp);
        int c2 = fun(a, n, i + 1, 1, dp);
        int a2 = Math.max(c1, c2);
        dp[i][free] = a2;
        return a2;
    }
}