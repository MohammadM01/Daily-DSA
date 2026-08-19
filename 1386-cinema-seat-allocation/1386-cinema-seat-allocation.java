class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 15;
        int mid = 60;
        int right = 240;
        for (int[] x : reservedSeats) {
            int row = x[0];
            int seat = x[1];
            if (seat >= 2 && seat <= 9)
                map.put(row, map.getOrDefault(row, 0) | (1 << (seat - 2)));
        }
        int ans = (n - map.size()) * 2;
        for (int mask : map.values()) {
            boolean a = (mask & left) == 0;
            boolean b = (mask & mid) == 0;
            boolean c = (mask & right) == 0;
            if (a && c)
                ans += 2;
            else if (a || b || c)
                ans++;
        }
        return ans;
    }
}