class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int v1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int v2 = nums[0] * nums[1] * nums[n-1];
        System.out.println(nums[0]);
        return Math.max(v1, v2);
    }
}