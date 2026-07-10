import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];

        int cid = 0;
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;

            if (i > 0 && arr[i][0] - arr[i - 1][0] > maxDiff)
                cid++;

            comp[arr[i][1]] = cid;
        }

        int[] next = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && arr[r + 1][0] - arr[l][0] <= maxDiff)
                r++;
            next[l] = r;
        }

        int LOG = 32 - Integer.numberOfLeadingZeros(n);;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++)
            up[0][i] = next[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {

            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            if (comp[u] != comp[v]) {
                ans[qi] = -1;
                continue;
            }

            int l = pos[u];
            int rr = pos[v];

            if (l > rr) {
                int t = l;
                l = rr;
                rr = t;
            }

            int jumps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][l] < rr) {
                    l = up[k][l];
                    jumps += 1 << k;
                }
            }

            ans[qi] = jumps + 1;
        }

        return ans;
    }
}