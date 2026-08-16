class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        StringBuilder dairy = new StringBuilder();
        fun(digits, digits.length(), 0, dairy, res, map);
        return res;
    }

    void fun(String s, int n, int idx, StringBuilder dairy,
            List<String> res, HashMap<Character, String> map) {
        if (idx == n) {
            res.add(dairy.toString());
            return;
        }
        String choices = map.get(s.charAt(idx));
        for (int j = 0; j < choices.length(); j++) {
            dairy.append(choices.charAt(j));
            fun(s, n, idx + 1, dairy, res, map);
            dairy.deleteCharAt(dairy.length() - 1);
        }
    }
}