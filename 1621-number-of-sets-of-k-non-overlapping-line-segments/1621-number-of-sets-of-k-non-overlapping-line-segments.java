class Solution {
    static final long MOD = 1000000007;

    public int numberOfSets(int n, int k) {
        return fun(n + k - 1, 2 * k);
    }

    int fun(int n, int k) {
        if (k > n - k)
            k = n - k;

        long ans = 1;

        for (int i = 1; i <= k; i++) {
            ans = ans * (n - k + i) % MOD;
            ans = ans * power(i, MOD - 2) % MOD;
        }

        return (int) ans;
    }

    long power(long a, long b) {
        long ans = 1;

        while (b > 0) {
            if (b % 2 == 1)
                ans = ans * a % MOD;

            a = a * a % MOD;
            b /= 2;
        }

        return ans;
    }
}