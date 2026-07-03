class Solution {
    List<int[]>[] graph;
    List<Integer> topo;
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        graph = new ArrayList[n];
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int high = 0;
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            high = Math.max(high, e[2]);
        }
        topo = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] e : graph[u]) {
                if (--indegree[e[0]] == 0) {
                    q.offer(e[0]);
                }
            }
        }
        int low = 0;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, online, k, n)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    boolean check(int limit, boolean[] online, long k, int n) {
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int u : topo) {
            if (dp[u] == Long.MAX_VALUE)
                continue;
            if (u != 0 && u != n - 1 && !online[u])
                continue;
            for (int[] e : graph[u]) {
                int v = e[0];
                int cost = e[1];
                if (cost < limit)
                    continue;
                if (v != 0 && v != n - 1 && !online[v])
                    continue;
                dp[v] = Math.min(dp[v], dp[u] + cost);
            }
        }

        return dp[n - 1] <= k;
    }
}