class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> st = new Stack<>();
        StringBuilder cur = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                st.push(cur);
                cur = new StringBuilder();
            }
            else if (ch == ')') {
                cur.reverse();

                StringBuilder temp = st.pop();
                temp.append(cur);

                cur = temp;
            }
            else {
                cur.append(ch);
            }
        }

        return cur.toString();
    }
}