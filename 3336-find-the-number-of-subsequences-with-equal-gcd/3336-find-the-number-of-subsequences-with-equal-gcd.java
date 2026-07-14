class Solution {

    int MOD = 1000000007;
    int n;
    int[] nums;
    Integer[][][] dp;

    public int subsequencePairCount(int[] nums) {

        this.nums = nums;
        n = nums.length;

        dp = new Integer[n + 1][201][201];

        return solve(0, 0, 0);
    }

    int solve(int i, int g1, int g2) {

        if (i == n) {
            if (g1 == g2 && g1 != 0)
                return 1;
            return 0;
        }

        if (dp[i][g1][g2] != null)
            return dp[i][g1][g2];

        long ans = 0;

        ans += solve(i + 1,
                gcd(g1, nums[i]),
                g2);

        ans += solve(i + 1,
                g1,
                gcd(g2, nums[i]));

        ans += solve(i + 1,
                g1,
                g2);

        ans %= MOD;

        return dp[i][g1][g2] = (int) ans;
    }

    int gcd(int a, int b) {

        if (a == 0)
            return b;

        return gcd(b % a, a);
    }
}