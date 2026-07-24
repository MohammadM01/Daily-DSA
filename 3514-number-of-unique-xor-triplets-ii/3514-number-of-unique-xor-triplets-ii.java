class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        boolean[] pair = new boolean[2048];
        boolean[] ans = new boolean[2048];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }
        for (int x = 0; x < 2048; x++) {
            if (pair[x]) {
                for (int num : nums) {
                    ans[x ^ num] = true;
                }
            }
        }
        int count = 0;
        for (boolean x : ans) {
            if (x)
                count++;
        }
        return count;
    }
}