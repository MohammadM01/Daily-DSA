class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = 0, sc = 0;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (classroom[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                }
                if (classroom[i].charAt(j) == 'L')
                    count++;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc, energy, 0});
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << count];
        visited[sr][sc][energy][0] = true;
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int moves = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int e = cur[2];
                int mask = cur[3];
                if (mask == (1 << count) - 1)
                    return moves;
                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;
                    if (classroom[nr].charAt(nc) == 'X')
                        continue;
                    if (e == 0)
                        continue;
                    int ne = e - 1;
                    int nmask = mask;
                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'L') {
                        int id = 0;
                        for (int i = 0; i < nr; i++) {
                            for (int j = 0; j < n; j++) {
                                if (classroom[i].charAt(j) == 'L')
                                    id++;
                            }
                        }
                        for (int j = 0; j < nc; j++) {
                            if (classroom[nr].charAt(j) == 'L')
                                id++;
                        }
                        nmask |= (1 << id);
                    }
                    if (ch == 'R')
                        ne = energy;
                    if (!visited[nr][nc][ne][nmask]) {
                        visited[nr][nc][ne][nmask] = true;
                        q.add(new int[]{nr, nc, ne, nmask});
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}