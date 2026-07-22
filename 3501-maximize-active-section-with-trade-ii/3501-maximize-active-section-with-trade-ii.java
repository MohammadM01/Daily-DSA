class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int ones = 0;
        for (char ch : arr) {
            if (ch == '1')
                ones++;
        }
        int[] start = new int[n];
        int[] len = new int[n];
        int[] group = new int[n];
        int groups = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0') {
                if (i > 0 && arr[i - 1] == '0') {
                    len[groups - 1]++;
                } else {
                    start[groups] = i;
                    len[groups] = 1;
                    groups++;
                }
            }
            group[i] = groups - 1;
        }
        List<Integer> ans = new ArrayList<>();

        if (groups == 0) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(ones);
            }
            return ans;
        }
        int m = groups - 1;
        int[][] table = null;
        if (m > 0) {
            int[] base = new int[m];
            for (int i = 0; i < m; i++) {
                base[i] = len[i] + len[i + 1];
            }
            int log = 32 - Integer.numberOfLeadingZeros(m);
            table = new int[log][];
            table[0] = base;
            for (int k = 1; k < log; k++) {
                int jump = 1 << (k - 1);
                int size = m - (1 << k) + 1;
                table[k] = new int[size];
                for (int i = 0; i < size; i++) {
                    table[k][i] = Math.max(table[k - 1][i],
                            table[k - 1][i + jump]);
                }
            }
        }
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int leftGroup = group[l];
            int rightGroup = group[r];
            int left = (leftGroup == -1)
                    ? -1
                    : len[leftGroup] - (l - start[leftGroup]);
            int right = (rightGroup == -1)
                    ? -1
                    : r - start[rightGroup] + 1;
            int endGroup = (arr[r] == '1')
                    ? rightGroup
                    : rightGroup - 1;
            int first = leftGroup + 1;
            int last = endGroup - 1;
            int res = ones;
            if (arr[l] == '0' && arr[r] == '0' && leftGroup + 1 == rightGroup) {

                res = Math.max(res, ones + left + right);

            } else if (first <= last) {
                int k = 31 - Integer.numberOfLeadingZeros(last - first + 1);
                int best = Math.max(
                        table[k][first],
                        table[k][last - (1 << k) + 1]);
                res = Math.max(res, ones + best);
            }
            if (arr[l] == '0' && leftGroup + 1 <= endGroup) {
                res = Math.max(res, ones + left + len[leftGroup + 1]);
            }
            if (arr[r] == '0' && leftGroup < rightGroup - 1) {
                res = Math.max(res, ones + right + len[rightGroup - 1]);
            }
            ans.add(res);
        }
        return ans;
    }
}