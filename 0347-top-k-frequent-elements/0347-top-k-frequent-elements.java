class Solution {
    class Pair {
        int first;
        int second;
        Pair(int f, int s) {
            this.first = f;
            this.second = s;
        }
    }

    public int[] topKFrequent(int nums[], int k) {
        HashMap <Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a,b) -> a.first != b.first
            ? a.first - b.first
            : a.second - b.second
        );

        for(int i : map.keySet()){
            int freq = map.get(i);
            Pair current = new Pair(freq,i);
            if(pq.size()<k)
                pq.add(current);
            else{
                if(current.first<pq.peek().first)
                    continue;
                pq.poll();
                pq.add(current);
            }
        }

        int res[]=new int[k];
        for(int i = 0; i<k;i++){
            res[i]=pq.peek().second;
            pq.poll();
        }
        return res;
    }
}