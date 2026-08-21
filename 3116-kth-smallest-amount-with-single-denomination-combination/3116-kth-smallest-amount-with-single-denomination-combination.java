class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long lo = 1;
        long hi = (long) coins[0] * k;
        for (int coin : coins) {
            hi = Math.min(hi, (long) coin * k);
        }
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (count(mid, coins) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    private long count(long x, int[] coins) {
        int n = coins.length;
        int m = 1 << n;
        long[] lcm = new long[m];
        lcm[0] = 1;
        long ans = 0;
        for (int mask = 1; mask < m; mask++) {
            int bit = Integer.numberOfTrailingZeros(mask);
            int prev = mask & (mask - 1);
            long a = lcm[prev];
            long b = coins[bit];
            long g = gcd(a, b);
            // If LCM would be greater than x, this subset contributes 0.
            if (a > x || a / g > x / b) {
                lcm[mask] = x + 1;
                continue;
            }
            lcm[mask] = (a / g) * b;
            long add = x / lcm[mask];
            // Odd number of coins -> add
            // Even number of coins -> subtract
            if (Integer.bitCount(mask) % 2 == 1) {
                ans += add;
            } else {
                ans -= add;
            }
        }
        return ans;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}