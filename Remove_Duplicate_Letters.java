/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/

public class Solution {
    public String removeDuplicateLetters(String s) {
        int i = 0, len = s.length();
        int[] cnt = new int[128];
        boolean[] inRes = new boolean[128];
        
        for (i = 0; i < len; i++) {
            cnt[s.charAt(i)]++;
        }
        
        // cnt[c] means the remaining count of the char c, including the position where current char c is.
        Stack<Character> stack = new Stack<Character>();
        for (i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (inRes[c]) {
                cnt[c]--;
                continue;
            }
            
            while (!stack.empty() && (stack.peek()) >= c && cnt[stack.peek()] > 0) {
                // keep the chars in stack in ascending order, and unique, unless the char in stack is the last one, then we need to keep it
                char temp = stack.pop();
                inRes[temp] = false;
            }
            
            stack.push(c);
            cnt[c]--;
            inRes[c] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
