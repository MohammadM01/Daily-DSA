class Solution {
    public int climbStairs(int n) {
        return fun(0, n);
    }
    int fun(int i, int n) {
        if (i == n || n==1)
            return 1;
        if (i > n)
            return 0;
        int current = 2;
        int previous = 1;
    
        for(i=3;i<=n;i++){
            int ans = current + previous;
            previous = current;
            current = ans;
        }
        return current;
    }
}