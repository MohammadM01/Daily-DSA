class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        set.add(s);
        q.add(s);
        boolean found = false;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String x = q.poll();
                if (check(x)) {
                    res.add(x);
                    found = true;
                }
                if (found)
                    continue;
                for (int i = 0; i < x.length(); i++) {
                    if (x.charAt(i) != '(' && x.charAt(i) != ')')
                        continue;
                    String next = x.substring(0, i) + x.substring(i + 1);
                    if (!set.contains(next)) {
                        set.add(next);
                        q.add(next);
                    }
                }
            }
            if (found)
                break;
        }
        return res;
    }
    boolean check(String s) {
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                balance++;
            else if (s.charAt(i) == ')') {
                balance--;
                if (balance < 0)
                    return false;
            }
        }
        return balance == 0;
    }
}