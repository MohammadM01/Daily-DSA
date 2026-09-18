class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - 'a';

            first[x] = Math.min(first[x], i);
            last[x] = i;
        }

        ArrayList<int[]> list = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1)
                continue;

            int l = first[c];
            int r = last[c];
            boolean ok = true;

            for (int i = l; i <= r; i++) {
                int x = s.charAt(i) - 'a';

                if (first[x] < l) {
                    ok = false;
                    break;
                }

                r = Math.max(r, last[x]);
            }

            if (ok)
                list.add(new int[]{l, r});
        }

        list.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();

        int end = -1;

        for (int i = 0; i < list.size(); i++) {
            int l = list.get(i)[0];
            int r = list.get(i)[1];

            if (l > end) {
                ans.add(s.substring(l, r + 1));
                end = r;
            }
        }

        return ans;
    }
}