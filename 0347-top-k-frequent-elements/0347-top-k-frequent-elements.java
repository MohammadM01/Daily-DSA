class Solution {

    class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int[] topKFrequent(int nums[], int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.first != b.first)
                    return a.first - b.first;

                return a.second - b.second;
            }
        );

        int i, freq;
        Pair curr;
        int res[];


        for (i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }


        for (int x : map.keySet()) {

            freq = map.get(x);
            curr = new Pair(freq, x);

            if (pq.size() < k) {
                pq.add(curr);
            }
            else if (curr.first > pq.peek().first) {
                pq.poll();
                pq.add(curr);
            }
        }

        res = new int[k];

        for (i = 0; i < k; i++) {
            res[i] = pq.peek().second;
            pq.poll();
        }

        return res;
    }
}