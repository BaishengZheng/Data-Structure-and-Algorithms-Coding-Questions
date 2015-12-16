/*
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
For example, Given s = “eceba”,
T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) return 0;
        if (s.length() == 0 || s.length() == 1) return s.length();
        // map value keep the rightmost index of elements in sliding window
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int left = 0, right = 0;
        int l = 0, r = 1;
        
        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (map.containsKey(c1) || map.size() < 2) {
                map.put(c1, right);
            } else {
                if (right - left > r - l) {
                    l = left;
                    r = right;
                }
                
                int small = s.length();
                for (Integer i : map.values()) {
                    small = Math.min(small, i);
                }
                
                left = small + 1;
                map.remove(s.charAt(small));
                map.put(c1, right);
            }
            right++;
        }
            
        if (right - left > r - l) {
          l = left;
          r = right;
        }
        return r - l;
    }
}

//O(n), if k distinct characters, this approach is better than the last one. But last one is better if we just have two distinct characters.
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) return 0;
        if (s.length() == 0 || s.length() == 1) return s.length();
        // map value keep the number of elements in sliding window
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int left = 0, right = 0;
        int l = 0, r = 1;
        
        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (map.containsKey(c1) || map.size() < 2) {
                map.put(c1, map.get(c1) == null ? 1 : map.get(c1) + 1);
            } else {
                if (right - left > r - l) {
                    l = left;
                    r = right;
                }
                
                while (map.size() >= 2) {  
                    char c2 = s.charAt(left);
                    map.put(c2, map.get(c2) - 1);
                    if (map.get(c2) == 0) map.remove(c2);
                    left++;
                }
                map.put(c1, 1);
            }
            right++;
        }
        
        if (right - left > r - l) {
          l = left;
          r = right;
        }
        return r - l;
    }
}
