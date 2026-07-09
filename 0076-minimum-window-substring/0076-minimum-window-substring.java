class Solution {

    public boolean f1(int have[], int need[]) {
        for (int i = 0; i < 256; i++) {
            if (have[i] < need[i])
                return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {

        int start = -1, l = 0, n = s.length(), m = t.length();
        int res = Integer.MAX_VALUE;
        int have[] = new int[256];
        int need[] = new int[256];
        if (n < m)
            return "";
        for (int i = 0; i < m; i++) {
            need[t.charAt(i)]++;
        }
        for (int h = 0; h < n; h++) {
            have[s.charAt(h)]++;
            while (f1(have, need)) {
                int len = h - l + 1;
                if (len < res) {
                    res = len;
                    start = l;
                }
                have[s.charAt(l)]--;
                l++;
            }
        }
        if (start == -1)
            return "";
        return s.substring(start, start + res);
    }
}