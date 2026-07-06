class Solution {
    public int characterReplacement(String s, int k) {
        int low = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int high = 0; high < s.length(); high++) {
            char ch = s.charAt(high);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            int len = high - low + 1;
            int maxFreq = 0;
            for (int val : map.values()) {
                maxFreq = Math.max(maxFreq, val);
            }
            int diff = len - maxFreq;
            while (diff > k) {
                char left = s.charAt(low);
                map.put(left, map.get(left) - 1);
                if (map.get(left) == 0) {
                    map.remove(left);
                }
                low++;
                len = high - low + 1;
                maxFreq = 0;
                for (int val : map.values()) {
                    maxFreq = Math.max(maxFreq, val);
                }
                diff = len - maxFreq;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
}