# 494. Target Sum - Medium

## Problem Statement:  

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.


### Example 1:  

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

### Example 2:  

Input: nums = [1], target = 1
Output: 1

### Constraints:

- 1 <= nums.length <= 20
- 0 <= nums[i] <= 1000
- 0 <= sum(nums[i]) <= 1000
- -1000 <= target <= 1000

## Solution Notes:  
- Dynamic Programming: Typical backpack problem， similar to 416， 
- The rest of (sum - target) are offset, so we only need to get combinations having sum value of offset
- Backtracking would have problems when nums[i] = 0

## Codes:
```TypeScript
// Dynamic Programming 2D Array
function findTargetSumWays(nums: number[], target: number): number {
    const sum = nums.reduce((acc, n) => acc + Math.abs(n), 0);
    if (Math.abs(target) > sum) return 0;
    if ((sum - Math.abs(target)) % 2 === 1) return 0;
    const offset = (sum - Math.abs(target)) / 2;
    const dp: number[][] = Array.from({length: nums.length}, () => new Array(offset + 1).fill(0));
    if (nums[0] <= offset) dp[0][Math.abs(nums[0])] = 1;
    let exp = 0;
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] === 0) exp++;
        dp[i][0] = 2 ** exp;
    }
    
    for (let i = 1; i < nums.length; i++) {
        for (let j = 1; j <= offset; j++) {
            if (j < Math.abs(nums[i])) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - Math.abs(nums[i])];
            }
        }
    }
    return dp[nums.length - 1][offset];
};
```

