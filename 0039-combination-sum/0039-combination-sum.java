class Solution {
    public void fun(int[] a, int n, int idx, List<Integer> diary,
                    int sum, List<List<Integer>> res, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(diary));
            return;
        }
        if (idx == n)
            return;
        if (sum + a[idx] <= target) {
            diary.add(a[idx]);
            fun(a, n, idx, diary, sum + a[idx], res, target);
            diary.remove(diary.size() - 1);
        }
        fun(a, n, idx + 1, diary, sum, res, target);
    }
    public List<List<Integer>> combinationSum(int[] a, int target) {
        List<List<Integer>> res = new ArrayList<>();
        fun(a, a.length, 0, new ArrayList<>(), 0, res, target);
        return res;
    }
}