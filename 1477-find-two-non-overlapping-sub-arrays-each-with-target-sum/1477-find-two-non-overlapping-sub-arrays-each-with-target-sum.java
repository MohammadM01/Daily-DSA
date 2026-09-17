class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int left = 0;
        int sum = 0;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        int ans = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int len = right - left + 1;
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE)
                    ans = Math.min(ans, len + best[left - 1]);
                min = Math.min(min, len);
            }
            best[right] = min;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}