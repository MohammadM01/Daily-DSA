class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        String ans = "";

        for (int i = 0; i < target.length(); i++) {

            int x = target.charAt(i) - 'a';

            for (int c = x + 1; c < 26; c++) {

                if (freq[c] == 0)
                    continue;

                freq[c]--;

                StringBuilder cur = new StringBuilder(target.substring(0, i));
                cur.append((char) ('a' + c));

                for (int j = 0; j < 26; j++) {
                    while (freq[j] > 0) {
                        cur.append((char) ('a' + j));
                        freq[j]--;
                    }
                }

                if (ans.equals("") || cur.toString().compareTo(ans) < 0)
                    ans = cur.toString();

       
                for (char ch : cur.substring(i + 1).toCharArray())
                    freq[ch - 'a']++;

                freq[c]++;
            }


            if (freq[x] > 0)
                freq[x]--;
            else
                break;
        }

        return ans;
    }
}