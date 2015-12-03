/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

class Solution {
    /*
    Edge test case for empty input array
    */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int cur = (i == nums.length) ? upper + 1 : nums[i];
            if (cur - prev >= 2) {
                res.add(getRange(prev + 1, cur - 1));
            }
            prev = cur;
        }
        return res;
    }
    
    private String getRange(int start, int end) {
        return (start == end) ? String.valueOf(start) : start + "->" + end;
    }
}
