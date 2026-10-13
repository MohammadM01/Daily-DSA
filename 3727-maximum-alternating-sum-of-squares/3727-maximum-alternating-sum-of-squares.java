class Solution {
    public long maxAlternatingSum(int[] nums) {

        Arrays.sort(nums);

        long ans = 0;
        int n = nums.length;

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++)
            a[i] = nums[i];

        Arrays.sort(a, (x, y) ->
            Long.compare((long) x * x, (long) y * y));

        for (int i = 0; i < n; i++) {
            long x = a[i];
            if (i < n / 2)
                ans -= x * x;
            else
                ans += x * x;
        }

        return ans;
    }
}