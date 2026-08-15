class Solution {
    public boolean isPalindrome(String s) {
        return fun(s, 0, s.length() - 1);
    }

    public boolean fun(String s, int l, int h) {
        if (l >= h)
            return true;
        if (!Character.isLetterOrDigit(s.charAt(l)))
            return fun(s, l + 1, h);
        if (!Character.isLetterOrDigit(s.charAt(h)))
            return fun(s, l, h - 1);
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(h)))
            return false;
        return fun(s, l + 1, h - 1);
    }
}