# 209. Minimum Size Subarray Sum - Medium

## Problem Statement:
Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

### Example 1:

**Input**: target = 7, nums = [2,3,1,2,4,3]
**Output**: 2
**Explanation**: The subarray [4,3] has the minimal length under the problem constraint.

### Example 2:

**Input**: target = 4, nums = [1,4,4]
**Output**: 1

### Example 3:

**Input**: target = 11, nums = [1,1,1,1,1,1,1,1]
**Output**: 0
 

### Constraints:

- 1 <= target <= 109
- 1 <= nums.length <= 105
- 1 <= nums[i] <= 104
 

### Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).

## Solution Notes:
- Two Pointers approach
- Sliding windows

## Codes:
-Python
```Python
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        l = 0
        s = 0
        min_len = float('inf')
        for i in range(len(nums)):
            s += nums[i]
            while s >= target:
                min_len = min(i - l + 1, min_len)
                s -= nums[l]
                l += 1
        if min_len == float('inf'):
            return 0
        else:
            return min_len
```

- JavaScript
```JavaScript
/**
 * @param {number} target
 * @param {number[]} nums
 * @return {number}
 */
var minSubArrayLen = function(target, nums) {
    let l = 0, sum = 0, res = Number.MAX_VALUE;
    for (let i = 0; i < nums.length; i++) {
        sum += nums[i];
        while (sum >= target) {
            res = Math.min(res, i - l + 1);
            sum -= nums[l];
            l++;
        }
    }
    return res == Number.MAX_VALUE ? 0 : res;
};
```

- Java
```Java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, sum = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                res = Math.min(res, i - l + 1);
                sum -= nums[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
```