# 283. Move Zeroes - Easy

## Problem Statement:

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

### Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

### Example 2:

Input: nums = [0]
Output: [0]
 
### Constraints:

- 1 <= nums.length <= 104
- -231 <= nums[i] <= 231 - 1

## Solution Notes:
- Two Pointers approach

## Codes:

- Python
```Python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        s, f = 0, 0
        for f in range(len(nums)):
            if nums[s] == 0:
                nums[s] = nums[f]
                nums[f] = 0
            
            if nums[s] != 0:
                s += 1
```

- JavaScript
```JavaScript
/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function(nums) {
    let s = 0, f = 0;
    for (f = 0; f < nums.length; f++) {
        if (nums[s] == 0) {
            nums[s] = nums[f];
            nums[f] = 0;
        } 

        if (nums[s] != 0) {
            s++;
        } 
    }
};
```

- Java
```Java
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        for (int j = index; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}
```