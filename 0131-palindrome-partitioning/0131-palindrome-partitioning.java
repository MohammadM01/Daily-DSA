class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        fun(s, 0, new ArrayList<>(), res);
        return res;
    }

    public void fun(String s, int idx, List<String> dairy, List<List<String>> res) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(dairy));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String a = s.substring(idx, i + 1);
            if (check(a)) {
                dairy.add(a);
                fun(s, i + 1, dairy, res);
                dairy.remove(dairy.size() - 1);
            }
        }
    }

    public boolean check(String s) {
        int l = 0, h = s.length() - 1;
        while (l < h) {
            if (s.charAt(l) != s.charAt(h))
                return false;
            l++;
            h--;
        }
        return true;
    }
}