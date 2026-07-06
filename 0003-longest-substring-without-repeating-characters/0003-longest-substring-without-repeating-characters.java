class Solution {
    public int lengthOfLongestSubstring(String s) {
        int low = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int high = 0; high < s.length(); high++) {
            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.get(ch) > 1) {
                char left = s.charAt(low);
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
                low++;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
}