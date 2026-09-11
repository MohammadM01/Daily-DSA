class Solution {
    public int totalNumbers(int[] digits) {

        int ans = 0;

        for (int i = 100; i <= 999; i++) {

            if (i % 2 != 0)
                continue;

            int a = i / 100;
            int b = (i / 10) % 10;
            int c = i % 10;

            int[] freq = new int[10];

            for (int j = 0; j < digits.length; j++)
                freq[digits[j]]++;

            if (freq[a] > 0) {
                freq[a]--;

                if (freq[b] > 0) {
                    freq[b]--;

                    if (freq[c] > 0)
                        ans++;
                }
            }
        }

        return ans;
    }
}
