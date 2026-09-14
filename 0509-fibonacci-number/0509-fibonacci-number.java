class Solution {
    public int fib(int n) {
        if (n == 0 || n == 1)
            return n;
        int current = 1;
        int previous = 0;
        for(int i=2;i<=n;i++){
            int ans = current + previous;
            previous=current;
            current = ans;
        }
        return current;
    }
}