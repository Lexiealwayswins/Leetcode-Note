# 416. Partition Equal Subset Sum - Medium

## Problem Statement:  

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.


### Example 1:  

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

### Example 2:  

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

### Constraints:

- 1 <= nums.length <= 200
- 1 <= nums[i] <= 100

## Solution Notes:  
- Dynamic Programming: Typical backpack problem
- Total weight and value is sum / 2, nums[i] is both weight and value

## Codes:
```TypeScript
// 1D Array
function canPartition(nums: number[]): boolean {
    const sum = nums.reduce((acc, n) => acc + n, 0);
    if (sum % 2 === 1) return false;
    const target = sum / 2;
    const dp: number[] = new Array(target + 1).fill(0);

    for (let i = 0; i < nums.length; i++) {
        for (let j = target; j >= nums[i]; j--) {
            dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
        }
    }
    if (dp[target] === target) return true;
    return false;
};

// 2D Array
function canPartition(nums: number[]): boolean {
    const sum = nums.reduce((acc, n) => acc + n, 0);
    if (sum % 2 === 1) return false;
    const target = sum / 2;
    const dp: number[][] = Array.from({length: nums.length}, () => new Array(target + 1).fill(0));
    for (let j = 1; j <= target; j++) {
        if (nums[0] <= j) dp[0][j] = nums[0];
    }

    for (let i = 1; i < nums.length; i++) {
        for (let j = 1; j <= target; j++) {
            if (nums[i] > j)  {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
            }
        }
    }
    if (dp[nums.length - 1][target] === target) return true;
    return false;
};
```

