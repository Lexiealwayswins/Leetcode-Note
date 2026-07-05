# 84. Largest Rectangle in Histogram - Hard

## Problem Statement:

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.


### Example 1:
![](https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg)

**Input:** heights = [2,1,5,6,2,3]
**Output:** 10
**Explanation:** The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

### Example 2:
![](https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg)

**Input:** heights = [2,4]
**Output:** 4
 
### Constraints:

- 1 <= heights.length <= 10^5
- 0 <= heights[i] <= 10^4

## Solution Notes:
- similar to problem 42
- add 0 to the two ends of target array

## Codes:
```Java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] new_heights = new int[heights.length + 2];
        System.arraycopy(heights, 0, new_heights, 1, heights.length);
        Stack<Integer> st = new Stack<>();
        int res = 0;
        st.push(0);
        for (int i = 1; i < new_heights.length; i++) {
            while (new_heights[st.peek()] > new_heights[i]) {
                int mid = st.pop();
                int w = i - st.peek() - 1;
                res = Math.max(res, w * new_heights[mid]);
            }
            st.push(i);
        }
        return res;
    }
}
```