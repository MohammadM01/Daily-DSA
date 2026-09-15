class Solution {
    public int maxProfit(int[] prices) {
        return fun(prices);
    }
    int fun(int a[]) {
        int previous = a[0];
        int current = 0;
        for (int i = 1; i < a.length; i++) {
            current = Math.max(current, a[i] - previous);
            previous = Math.min(previous, a[i]);
        }
        return current;
    }
}