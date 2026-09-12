class Solution {

    class State {
        long score;
        int[] a;

        State(long score, int[] a) {
            this.score = score;
            this.a = a;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);

            return Integer.compare(x[3], y[3]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int low = i + 1;
            int high = n;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (a[mid][0] > a[i][1])
                    high = mid;
                else
                    low = mid + 1;
            }

            next[i] = low;
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++)
            dp[n][k] = new State(0, new int[0]);

        for (int i = n - 1; i >= 0; i--) {

            dp[i][0] = new State(0, new int[0]);

            for (int k = 1; k <= 4; k++) {

                State skip = dp[i + 1][k];

                State takeNext = dp[next[i]][k - 1];

                int[] b = new int[takeNext.a.length + 1];

                b[0] = a[i][3];

                for (int j = 0; j < takeNext.a.length; j++)
                    b[j + 1] = takeNext.a[j];

                Arrays.sort(b);

                State take = new State(a[i][2] + takeNext.score, b);

                if (take.score > skip.score) {
                    dp[i][k] = take;
                } else if (take.score < skip.score) {
                    dp[i][k] = skip;
                } else {
                    if (compare(take.a, skip.a) < 0)
                        dp[i][k] = take;
                    else
                        dp[i][k] = skip;
                }
            }
        }

        return dp[0][4].a;
    }

    int compare(int[] a, int[] b) {

        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i])
                return Integer.compare(a[i], b[i]);
        }

        return Integer.compare(a.length, b.length);
    }
}