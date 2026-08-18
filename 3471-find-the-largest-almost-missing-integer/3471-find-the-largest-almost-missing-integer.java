class Solution {
    public int largestInteger(int[] nums, int k) {

        int[] freq = new int[51];

        for (int i = 0; i <= nums.length - k; i++) {
            boolean[] used = new boolean[51];

            for (int j = i; j < i + k; j++) {
                used[nums[j]] = true;
            }

            for (int x = 0; x <= 50; x++) {
                if (used[x])
                    freq[x]++;
            }
        }

        for (int x = 50; x >= 0; x--) {
            if (freq[x] == 1)
                return x;
        }

        return -1;
    }
}