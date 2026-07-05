# 503. Next Greater Element II - Medium

## Problem Statement:

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 
### Example 1:

**Input:** nums = [1,2,1]
**Output:** [2,-1,2]
**Explanation:** The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.

### Example 2:

**Input:** nums = [1,2,3,4,3]
**Output:** [2,3,4,-1,4]
 

### Constraints:

- 1 <= nums.length <= 104
- -109 <= nums[i] <= 109

## Solution Notes:
- similar to problem 496
- use a stack to store the index of decreasing numbers

## Codes:

```Java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int j;
        for (int i = 0; i < 2 * nums.length; i++) {
            j = i % nums.length;
            while (!st.isEmpty() && nums[st.peek()] < nums[j] ) {
                int idx = st.pop();
                res[idx] = nums[j];
            }
            st.push(j);
        }
        return res;
    }
}
```