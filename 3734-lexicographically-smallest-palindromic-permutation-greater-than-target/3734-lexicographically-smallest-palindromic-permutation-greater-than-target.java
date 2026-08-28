class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int odd = -1;
        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                if (n % 2 == 0 || odd != -1) {
                    return "";
                }
                odd = i;
            }
        }
        int[] hc = new int[26];
        for (int i = 0; i < 26; i++) {
            hc[i] = cnt[i] / 2;
        }
        String ans = null;
        for (int p = half - 1; p >= 0; p--) {
            int[] left = hc.clone();
            boolean ok = true;
            for (int j = 0; j < p; j++) {
                int x = target.charAt(j) - 'a';
                if (left[x] == 0) {
                    ok = false;
                    break;
                }
                left[x]--;
            }
            if (!ok) {
                continue;
            }
            int x = target.charAt(p) - 'a';
            for (int c = x + 1; c < 26; c++) {
                if (left[c] == 0) {
                    continue;
                }
                left[c]--;
                StringBuilder h = new StringBuilder();
                for (int j = 0; j < p; j++) {
                    h.append(target.charAt(j));
                }
                h.append((char) ('a' + c));
                for (int j = 0; j < 26; j++) {
                    while (left[j] > 0) {
                        h.append((char) ('a' + j));
                        left[j]--;
                    }
                }
                String cur = makePalindrome(h.toString(), odd);
                if (cur.compareTo(target) > 0) {
                    if (ans == null || cur.compareTo(ans) < 0) {
                        ans = cur;
                    }
                }
                break;
            }
        }
        int[] left = hc.clone();
        boolean ok = true;
        for (int i = 0; i < half; i++) {
            int x = target.charAt(i) - 'a';
            if (left[x] == 0) {
                ok = false;
                break;
            }
            left[x]--;
        }
        if (ok) {
            StringBuilder h = new StringBuilder();

            for (int i = 0; i < half; i++) {
                h.append(target.charAt(i));
            }
            String cur = makePalindrome(h.toString(), odd);
            if (cur.compareTo(target) > 0) {
                if (ans == null || cur.compareTo(ans) < 0) {
                    ans = cur;
                }
            }
        }
        return ans == null ? "" : ans;
    }
    private String makePalindrome(String h, int odd) {
        StringBuilder res = new StringBuilder();
        res.append(h);

        if (odd != -1) {
            res.append((char) ('a' + odd));
        }
        for (int i = h.length() - 1; i >= 0; i--) {
            res.append(h.charAt(i));
        }
        return res.toString();
    }
}