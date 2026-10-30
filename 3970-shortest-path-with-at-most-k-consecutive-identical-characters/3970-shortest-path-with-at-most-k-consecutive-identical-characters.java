class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long cost = cur[0];
            int node = (int) cur[1];
            int cnt = (int) cur[2];

            if (cost != dist[node][cnt])
                continue;

            if (node == n - 1)
                return (int) cost;

            for (int[] edge : adj[node]) {
                int next = edge[0];
                int weight = edge[1];

                int newCnt;

                if (labels.charAt(node) == labels.charAt(next))
                    newCnt = cnt + 1;
                else
                    newCnt = 1;

                if (newCnt > k)
                    continue;

                long newCost = cost + weight;

                if (newCost < dist[next][newCnt]) {
                    dist[next][newCnt] = newCost;
                    pq.offer(new long[]{newCost, next, newCnt});
                }
            }
        }

        return -1;
    }
}