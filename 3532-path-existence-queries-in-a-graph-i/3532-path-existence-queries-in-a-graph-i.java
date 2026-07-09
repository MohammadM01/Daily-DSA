class Solution {

    int[] parent;

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n - 1; i++) {

            if (nums[i + 1] - nums[i] <= maxDiff) {
                union(i, i + 1);
            }
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < queries.length; i++) {

            ans[i] = find(queries[i][0]) == find(queries[i][1]);
        }

        return ans;
    }

    int find(int x) {

        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    void union(int a, int b) {

        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pa] = pb;
        }
    }
}