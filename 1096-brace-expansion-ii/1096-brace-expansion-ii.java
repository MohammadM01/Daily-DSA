class Solution {
    int idx;
    public List<String> braceExpansionII(String expression) {
        idx = 0;
        Set<String> set = fun(expression);
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
    Set<String> fun(String s) {
        Set<String> res = new HashSet<>();
        Set<String> cur = new HashSet<>();
        cur.add("");
        while (idx < s.length() && s.charAt(idx) != '}') {
            if (s.charAt(idx) == ',') {
                idx++;
                res.addAll(cur);
                cur = new HashSet<>();
                cur.add("");
            }
            else if (s.charAt(idx) == '{') {
                idx++;
                Set<String> next = fun(s);
                idx++;
                cur = combine(cur, next);
            }
            else {
                String ch = "" + s.charAt(idx);
                idx++;
                Set<String> next = new HashSet<>();
                next.add(ch);
                cur = combine(cur, next);
            }
        }
        res.addAll(cur);
        return res;
    }
    Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> res = new HashSet<>();
        for (String x : a) {
            for (String y : b) {
                res.add(x + y);
            }
        }
        return res;
    }
}