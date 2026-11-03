class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        int[][] dp = new int[26][26];

        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);

        String s = words[0];

        int first = s.charAt(0) - 'a';
        int last = s.charAt(s.length() - 1) - 'a';

        dp[first][last] = s.length();

        for (int i = 1; i < n; i++) {
            String word = words[i];

            int x = word.charAt(0) - 'a';
            int y = word.charAt(word.length() - 1) - 'a';

            int[][] next = new int[26][26];

            for (int[] row : next)
                Arrays.fill(row, Integer.MAX_VALUE);

            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    if (dp[j][k] == Integer.MAX_VALUE)
                        continue;
                    int len = dp[j][k] + word.length();
                    int a = len - (k == x ? 1 : 0);
                    next[j][y] = Math.min(next[j][y], a);
                    int b = len - (y == j ? 1 : 0);
                    next[x][k] = Math.min(next[x][k], b);
                }
            }

            dp = next;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }

        return ans;
    }
}