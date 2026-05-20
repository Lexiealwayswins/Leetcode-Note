# 977. Squares of a Sorted Array

## Problem Statement:
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 
### Example 1:

**Input:** nums = [-4,-1,0,3,10]
**Output:** [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].

### Example 2:

**Input:** nums = [-7,-3,2,3,11]
**Output:** [4,9,9,49,121]

 
### Constraints:

- 1 <= nums.length <= 104
- -104 <= nums[i] <= 104
- nums is sorted in non-decreasing order.
 

### Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?

## Solution Notes:
- Two Pointers approach
- Compare the two sides to see whether the square of negative numbers are bigger than the square of positive numbers

## Codes:
- Python
```Python
class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        l, r, i = 0, len(nums) - 1, len(nums) - 1
        res = [0] * len(nums)
        while l <= r:
            if nums[l] * nums[l] < nums[r] * nums[r]:
                res[i] = nums[r] * nums[r]
                r -= 1
            else:
                res[i] = nums[l] * nums[l]
                l += 1
            i -= 1
        
        return res
```

- JavaScript
```JavaScript
/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortedSquares = function(nums) {
    let l = 0, r = nums.length - 1;
    let i = nums.length - 1;
    let res = [];
    while (l <= r) {
        if (nums[r] * nums[r] > nums[l] * nums[l]) {
            res[i] = nums[r] * nums[r];
            r--;
        } else {
            res[i] = nums[l] * nums[l];
            l++
        }
        i--;
    }
    return res;
};
```

- Java
```Java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] result = new int[nums.length];
        int index = right;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
            
    }
}
```