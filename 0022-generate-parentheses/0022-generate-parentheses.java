class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        generate(n, 0, 0, new StringBuilder());
        return ans;
    }
    void generate(int n, int open, int close, StringBuilder sb) {
        if (open == n && close == n) {
            ans.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            generate(n, open + 1, close, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            generate(n, open, close + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}