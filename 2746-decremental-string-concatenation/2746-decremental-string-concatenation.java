class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("" + words[0].charAt(0) + words[0].charAt(words[0].length() - 1),
                words[0].length());

        for (int i = 1; i < words.length; i++) {
            String word = words[i];

            char s1 = word.charAt(0);
            char e1 = word.charAt(word.length() - 1);
            int l1 = word.length();

            HashMap<String, Integer> next = new HashMap<>();

            for (String key : map.keySet()) {
                char s2 = key.charAt(0);
                char e2 = key.charAt(1);
                int l2 = map.get(key);
                String key1 = "" + s1 + e2;
                int a = l1 + l2 - (e1 == s2 ? 1 : 0);
                next.put(key1, Math.min(next.getOrDefault(key1, Integer.MAX_VALUE), a));
                String key2 = "" + s2 + e1;
                int b = l2 + l1 - (e2 == s1 ? 1 : 0);

                next.put(key2, Math.min(next.getOrDefault(key2, Integer.MAX_VALUE), b));
            }

            map = next;
        }

        int ans = Integer.MAX_VALUE;

        for (int val : map.values())
            ans = Math.min(ans, val);

        return ans;
    }
}