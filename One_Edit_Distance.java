/*
Given two strings S and T, determine if they are both one edit distance apart.
*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() == t.length()) {
            return oneEditReplace(s, t);
        } else if (s.length() + 1 == t.length()) {
            return oneEditInsert(s, t);
        } else if (s.length() == t.length() + 1) {
            return oneEditInsert(t, s);
        }
        return false;
    }
    
    private boolean oneEditReplace(String s1, String s2) {
        // Assuming s1.length() == s2.length(), 
        // return true only and have to be one different char between s1 and s2
        int index = 0;
        boolean foundDiff = false;
        while (index < s1.length()) {
            if (s1.charAt(index) != s2.charAt(index)) {
                if (foundDiff) {
                    return false;
                }
                foundDiff = true;
            }
            index++;
        }
        return foundDiff;
    }
    
    private boolean oneEditInsert(String s1, String s2) {
        // Assuming s1.length() + 1 = s2.length()
        int index1 = 0, index2 = 0;
        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }
}

//Code refactor
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        // length check
        if (Math.abs(s.length() - t.length()) > 1) return false;
        
        String s1 = s.length() < t.length() ? s : t;
        String s2 = s.length() < t.length() ? t : s;
        
        int index1 = 0, index2 = 0;
        boolean foundDiff = false;
        while (index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (foundDiff) {
                    return false;
                }
                foundDiff = true;
                
                if (s1.length() == s2.length()) {
                    index1++;
                }
            } else {
                index1++;
            }
            index2++;
        }
        return foundDiff || s1.length() < s2.length();
    }
}
