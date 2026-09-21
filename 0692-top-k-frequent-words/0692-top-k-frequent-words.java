class Solution {
    class Pair {
        String first;
        int second;

        Pair(String f, int s) {
            this.first = f;
            this.second = s;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++)
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);

        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.second != b.second)
                    return a.second - b.second;

                return b.first.compareTo(a.first);
            }
        );

        String[] keys = map.keySet().toArray(new String[0]);

        for (int i = 0; i < keys.length; i++) {

            String word = keys[i];
            int freq = map.get(word);

            Pair current = new Pair(word, freq);

            if (pq.size() < k) {
                pq.add(current);
            }
            else {
                Pair top = pq.peek();

                if (current.second < top.second)
                    continue;

                if (current.second == top.second &&
                    current.first.compareTo(top.first) > 0)
                    continue;

                pq.poll();
                pq.add(current);
            }
        }

        for (int i = 0; i < k; i++) {
            res.add(pq.poll().first);
        }

        Collections.reverse(res);

        return res;
    }
}