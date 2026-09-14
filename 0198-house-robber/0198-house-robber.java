class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        return fun(nums, n, 0, 0, 0);
    }
    int fun(int a[], int n, int i, int previous, int current) {
        if (i == n)
            return current;
        int ans = Math.max(current, previous + a[i]);
        return fun(a, n, i + 1, current, ans);
    }
}