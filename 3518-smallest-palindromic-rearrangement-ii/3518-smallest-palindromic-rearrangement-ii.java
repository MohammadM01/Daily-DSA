class Solution {

    long nCr(int n, int r, int limit) {

        r = Math.min(r, n - r);
        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;

            if (res > limit)
                return limit + 1L;
        }

        return res;
    }

    long countWays(int[] freq, int total, int limit) {

        long ways = 1;

        for (int x : freq) {

            if (x == 0)
                continue;

            ways *= nCr(total, x, limit);

            if (ways > limit)
                return limit + 1L;

            total -= x;
        }

        return ways;
    }

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        int n = s.length();
        int half = n / 2;

        char[] ans = new char[n];

        for (int i = 0; i < 26; i++) {

            if ((freq[i] & 1) == 1)
                ans[half] = (char) ('a' + i);

            freq[i] /= 2;
        }

        if (countWays(freq, half, k) < k)
            return "";

        for (int i = 0; i < half; i++) {

            for (int j = 0; j < 26; j++) {

                if (freq[j] == 0)
                    continue;

                freq[j]--;

                long ways = countWays(freq, half - i - 1, k);

                if (ways >= k) {
                    ans[i] = (char) ('a' + j);
                    break;
                }

                k -= ways;
                freq[j]++;
            }
        }

        for (int i = 0; i < half; i++)
            ans[n - 1 - i] = ans[i];

        return new String(ans);
    }
}