class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Build adjacency list and indegree for topological sort
        List<List<int[]>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            graph.get(u).add(new int[]{v, cost});
            indegree[v]++;
        }
        
        // Get topological order
        List<Integer> topo = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topo.add(node);
            for (int[] next : graph.get(node)) {
                int v = next[0];
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        
        // Binary search on answer
        int low = 0, high = (int)1e9;
        int result = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReachWithScore(edges, online, k, n, topo, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return result;
    }
    
    private boolean canReachWithScore(int[][] edges, boolean[] online, long k, 
                                      int n, List<Integer> topo, int minScore) {
        // Build graph with only edges that have cost >= minScore
        List<List<int[]>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            if (cost >= minScore) {
                graph.get(u).add(new int[]{v, cost});
                indegree[v]++;
            }
        }
        
        // DP to find shortest path from 0 to n-1
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[0] = 0;
        
        // Only consider nodes that are online
        if (!online[0]) return false;
        
        for (int node : topo) {
            if (!online[node] && node != 0 && node != n - 1) continue;
            if (dist[node] == Long.MAX_VALUE / 2) continue;
            
            for (int[] next : graph.get(node)) {
                int v = next[0], cost = next[1];
                if (!online[v] && v != 0 && v != n - 1) continue;
                if (dist[node] + cost < dist[v]) {
                    dist[v] = dist[node] + cost;
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}
