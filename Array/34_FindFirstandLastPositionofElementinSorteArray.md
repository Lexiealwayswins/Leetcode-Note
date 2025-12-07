# 34. Find First and Last Position of Element in Sorted Array - Medium

## Problem Statement:

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

### Example 1:

**Input**: nums = [5,7,7,8,8,10], target = 8
**Output**: [3,4]

### Example 2:

**Input**: nums = [5,7,7,8,8,10], target = 6
**Output**: [-1,-1]

### Example 3:

**Input**: nums = [], target = 0
**Output**: [-1,-1]


### Constraints:
- 0 <= nums.length <= 105
- -109 <= nums[i] <= 109
- nums is a non-decreasing array.
- -109 <= target <= 109

## Solution Notes:
- Binary Search
- be careful about the boundary

## Codes:
- Python
```Python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        first = self.getFirst(nums, target)
        last = self.getLast(nums, target)
        return [first, last]
    
    def getFirst(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            m = l + (r - l) // 2
            if nums[m] < target:
                l = m + 1
            elif nums[m] > target:
                r = m - 1
            else:
                if (m == 0) or (nums[m - 1] < target): 
                    return m
                r = m - 1
        return -1

    def getLast(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l <= r:
            m = l + (r - l) // 2
            if nums[m] < target:
                l = m + 1
            elif nums[m] > target:
                r = m - 1
            else:
                if (m == len(nums) - 1) or (nums[m + 1] > target): 
                    return m
                l = m + 1
        return -1
```

- JavaScript
```JavaScript
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function(nums, target) {
    let first = getFirst(nums, target);
    let last = getLast(nums, target);
    return [first, last]
    
    function getFirst(nums, target) {
        let l = 0, r = nums.length - 1;
        while (l <= r) {
            let m = l + Math.floor((r - l) / 2);
            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                if (m == 0 || nums[m - 1] < target) return m
                r = m - 1
            }
        }
        return -1;
    }

    function getLast(nums, target) {
        let l = 0, r = nums.length - 1;
        while (l <= r) {
            let m = l + Math.floor((r - l) / 2);
            if (nums[m] < target) {
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                if (m == nums.length - 1 || nums[m + 1] > target) return m
                l = m + 1
            }
        }
        return -1;
    }
};
```

- Java
```Java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    public int findFirst (int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                if (m == 0 || nums[m - 1] < target) return m;
                r = m - 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public int findLast (int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                if (m == nums.length - 1 || nums[m + 1] > target) return m;
                l = m + 1;
            } else if (nums[m] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
```