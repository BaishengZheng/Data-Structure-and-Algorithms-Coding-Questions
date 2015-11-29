/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
*/

public class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null) return -1;
        int n = nums.length;
        
        int[] array = new int[n + 2];
        for (int i = 0; i < n; i++) {
            array[i + 1] = nums[i];
        }
        array[0] = array[n + 1] = 1;
        
        /* rangeValue[i][j] represents the max coins it can get from range i to j in new array which have boundary "1" attached */
        int[][] rangeValue = new int[n+2][n+2];
        
        // Build up from smaller ranges to larger ranges
        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n + 1 - len; left++) {
                int right = left + len - 1;
                // calculate the max # of coins could be obtained by popping ballons only 
                // in range [left, right], in which the final popped ballon would be within [left, right]
                for (int fnl = left; fnl <= right; fnl++) {
                    rangeValue[left][right] = Math.max(rangeValue[left][right], 
                        rangeValue[left][fnl-1] + rangeValue[fnl+1][right] + array[left-1]*array[fnl]*array[right+1]);
                }
            }
        }
        return rangeValue[1][n];
    }
}
