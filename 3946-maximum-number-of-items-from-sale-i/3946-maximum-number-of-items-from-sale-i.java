class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] gain = new int[n];

        for(int i = 0; i < n; i++){
            int f = items[i][0];

            for(int j = 0; j < n; j++){
                if (i != j && items[j][0] % f == 0)
                    gain[i]++;
            }
        }

        int[] dp = new int[budget + 1];

        for(int i = 0; i < n; i++){
            int cost = items[i][1];
            int first = gain[i] + 1;

            int[] newDp = dp.clone();

            for(int b = 0; b + cost <= budget; b++){
                newDp[b + cost] = Math.max(newDp[b + cost], dp[b] + first);
            }

            for(int b = cost; b <= budget; b++){
                newDp[b] = Math.max(newDp[b], newDp[b - cost] + 1);
            }
            dp = newDp;
        }

        int ans = 0;

        for (int val : dp)
            ans = Math.max(ans, val);

        return ans;
    }
}