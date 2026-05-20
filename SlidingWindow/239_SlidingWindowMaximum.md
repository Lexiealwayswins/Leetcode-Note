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
- Record the index of max number in a sliding window using a deque, which follows a descending order
- Use index rather than number value to cover case like [-7,-8,7,5,7,1,6,0]

## Codes:
```Python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        deque = collections.deque()
        res = []

        for i in range(len(nums)):
            if len(deque) > 0 and i - k >= deque[0]:
                deque.popleft()
            while len(deque) > 0 and nums[deque[-1]] <= nums[i]:
                deque.pop()
            
            deque.append(i)
        
            if i - k + 1 >= 0:
                res.append(nums[deque[0]])

        return res
```

```JavaScript
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function(nums, k) {
    let deque = [];
    let res = []
    for (let i = 0; i < nums.length; i++) {
        if (deque.length && i - k >= deque[0]) {
            deque.shift();
        }
        while (deque.length && nums[i] >= nums[deque[deque.length - 1]]) {
            deque.pop();
        }
        deque.push(i);

        if (i >= k - 1) {
            res.push(nums[deque[0]]);
        }

    }
    return res;
};
```

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