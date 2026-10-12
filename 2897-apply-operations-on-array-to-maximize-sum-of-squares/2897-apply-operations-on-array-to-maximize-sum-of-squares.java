class Solution {
    public int maxSum(List<Integer> nums, int k) {

        int mod = 1000000007;
        int[] cnt = new int[32];

        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) != 0)
                    cnt[i]++;
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int j = 0; j < 32; j++) {
                if (cnt[j] > 0) {
                    x |= (1L << j);
                    cnt[j]--;
                }
            }

            ans = (ans + x * x) % mod;
        }

        return (int) ans;
    }
}