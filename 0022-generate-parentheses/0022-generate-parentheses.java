class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder dairy = new StringBuilder();
        fun(n, 0, 0, dairy, res);
        return res;
    }
    void fun(int n, int open, int close,
             StringBuilder dairy, List<String> res) {
        if (open == n && close == n) {
            res.add(dairy.toString());
            return;
        }
        if (open < n) {
            dairy.append('(');
            fun(n, open + 1, close, dairy, res);
            dairy.deleteCharAt(dairy.length() - 1);
        }
        if (close < open) {
            dairy.append(')');
            fun(n, open, close + 1, dairy, res);
            dairy.deleteCharAt(dairy.length() - 1);
        }
    }
}