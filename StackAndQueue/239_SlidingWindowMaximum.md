# 239. Sliding Window Maximum - Hard

## Problem Statement:

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

### Example 1:

**Input:** nums = [1,3,-1,-3,5,3,6,7], k = 3  
**Output:** [3,3,5,5,6,7]  
**Explanation:**   
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3  
 1 [3  -1  -3] 5  3  6  7       3  
 1  3 [-1  -3  5] 3  6  7       5  
 1  3  -1 [-3  5  3] 6  7       5  
 1  3  -1  -3 [5  3  6] 7       6  
 1  3  -1  -3  5 [3  6  7]      7  

### Example 2:

**Input:** nums = [1], k = 1  
**Output:** [1]
 

## Constraints:

- 1 <= nums.length <= 105
- -104 <= nums[i] <= 104
- 1 <= k <= nums.length

## Solution Notes:
- Record the max number in a sliding window using a deque.

## Codes:

```Java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 1 || k == 1) return nums;

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
```Java