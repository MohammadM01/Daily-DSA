class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int freq[] = new int[n + 1];

        for (int i = 0; i < n; i++) {
            freq[nums[i]]++;
        }

        int duplicate = 0;
        int missing = 0;

        for (int i = 1; i <= n; i++) {
            if (freq[i] == 2)
                duplicate = i;

            if (freq[i] == 0)
                missing = i;
        }

        return new int[]{duplicate, missing};
    }
}