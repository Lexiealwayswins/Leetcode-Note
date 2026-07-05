# 300. Longest Increasing Subsequence - medium

## Problem Statement:  

Given an integer array nums, return the length of the longest strictly increasing subsequence.
 

### Example 1:

**Input:** nums = [10,9,2,5,3,7,101,18]
**Output:** 4
**Explanation:** The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

### Example 2:

**Input:** nums = [0,1,0,3,2,3]
**Output:** 4

### Example 3:

**Input:** nums = [7,7,7,7,7,7,7]
**Output:** 1
 
###  Constraints:

- 1 <= nums.length <= 2500
- -104 <= nums[i] <= 104
 

### Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

## Solution Notes:  
- Dynamic Programming
- dp[i] is the longest increasing subsequence from 0 to j + 1
- because we only update dp[i] when we found a increasing number, we need a res to store the result
- for example:
- [1, 3, 6, 7, 9, 4, 10, 5, 6], this array's dp should be:
- [1, 2, 3, 4, 5, 3,  6, 4, 5]
- if we return dp[-1], it will get the wrong result of 5 rather than 6.

## Codes:
```Python
# DP
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if len(nums) <= 1: return len(nums)
        dp = [1] * len(nums)
        res = 0
        for i in range(1, len(nums)):
            for j in range(i):
                if nums[i] > nums[j]:
                    dp[i] = max(dp[i], dp[j] + 1)
                if dp[i] > res: res = dp[i]
        return res

# Greedy
class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return len(nums)
        
        tails = [nums[0]]  # 存储递增子序列的尾部元素
        for num in nums[1:]:
            if num > tails[-1]:
                tails.append(num)  # 如果当前元素大于递增子序列的最后一个元素，直接加入到子序列末尾
            else:
                # 使用二分查找找到当前元素在递增子序列中的位置，并替换对应位置的元素
                left, right = 0, len(tails) - 1
                while left < right:
                    mid = (left + right) // 2
                    if tails[mid] < num:
                        left = mid + 1
                    else:
                        right = mid
                tails[left] = num
        
        return len(tails)  # 返回递增子序列的长度

```

