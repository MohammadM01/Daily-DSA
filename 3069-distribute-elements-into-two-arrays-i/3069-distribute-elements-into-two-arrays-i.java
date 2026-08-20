class Solution {
    public int[] resultArray(int[] nums) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        int[] ans = new int[nums.length];
        int k = 0;
        a.add(nums[0]);
        b.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (a.get(a.size() - 1) > b.get(b.size() - 1)) {
                a.add(nums[i]);
            } else {
                b.add(nums[i]);
            }
        }
        for (int i = 0; i < a.size(); i++)
            ans[k++] = a.get(i);
        for (int i = 0; i < b.size(); i++)
            ans[k++] = b.get(i);
        return ans;
    }
}
