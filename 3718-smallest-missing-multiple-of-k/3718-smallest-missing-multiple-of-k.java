class Solution {
    public int missingMultiple(int[] nums, int k) {
        for (int x = k; ; x += k) {
            boolean found = false;
            for (int num : nums) {
                if (num == x) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return x;
        }
    }
}