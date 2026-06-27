class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put((long) x, map.getOrDefault((long) x, 0) + 1);
        }
        int ans = 1;
        if (map.containsKey(1L)) {
            int cnt = map.get(1L);
            if (cnt % 2 == 0)
                cnt--;
            ans = Math.max(ans, cnt);
        }
        for (long x : map.keySet()) {
            if (x == 1)
                continue;
            long curr = x;
            int len = 0;
            while (map.getOrDefault(curr, 0) >= 2) {
                len += 2;
                if (curr > 1000000000L)
                    break;

                curr = curr * curr;
            }
            if (map.getOrDefault(curr, 0) == 1)
                len++;
            else if (len > 0)
                len--;
            ans = Math.max(ans, len);
        }
        return ans;
    }
}