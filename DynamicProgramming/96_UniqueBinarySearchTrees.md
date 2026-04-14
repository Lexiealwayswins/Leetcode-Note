# 96. Unique Binary Search Trees - Medium(Hard)

## Problem Statement:  

Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

### Example 1:  
![](https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg)

**Input:**: n = 3
**Output:**: 5

### Example 2:  
**Input:**: n = 1
**Output:**: 1

### Constraints:

- 1 <= n <= 19

## Solution Notes:  
- Dynamic Programming 
- dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
- dp[i] += dp[j - 1] * dp[i - j]


## Codes:
```TypeScript
function numTrees(n: number): number {
    let dp: number[] = new Array(n + 1).fill(0);
    dp[0] = 1;
    dp[1] = 1;
    for (let i = 2; i <= n; i++) {
        for (let j = 1; j <= i; j++) {
            dp[i] += dp[j - 1] * dp[i - j]
        }
    }
    return dp[n];
};
```

