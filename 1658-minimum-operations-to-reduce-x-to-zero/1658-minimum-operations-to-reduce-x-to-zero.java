class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        int left = 0;
        int sum = 0;
        int max = -1;
        for (int i = 0; i < nums.length; i++)
            total += nums[i];
        int target = total - x;
        if (target < 0)
            return -1;
        if (target == 0)
            return nums.length;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > target) {
                sum -= nums[left];
                left++;
            }
            if (sum == target)
                max = Math.max(max, right - left + 1);
        }
        if (max == -1)
            return -1;
        return nums.length - max;
    }
}