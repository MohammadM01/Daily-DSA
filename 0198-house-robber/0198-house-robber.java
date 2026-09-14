class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        return fun(nums, n);
    }

    public int fun(int a[], int n) {
        if (n == 0)
            return 0;

        if (n == 1)
            return a[0];

        int previous = 0;
        int current = a[0];

        for (int i = 1; i < n; i++) {
            int ans = Math.max(current, previous + a[i]);
            previous = current;
            current = ans;
        }

        return current;
    }
}