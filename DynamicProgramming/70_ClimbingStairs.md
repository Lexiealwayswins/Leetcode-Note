# 70. Climbing Stairs - Easy

## Problem Statement:  

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


### Example 1:  

**input:** n = 2
**Output:**: 2
**Explanation:**: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

### Example 2:  

**input:** n = 3
**Output:**: 3
**Explanation:**: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

### Constraints:

- 1 <= n <= 45

## Solution Notes:  
- Dynamic Programming 
- climb i stairs can be dp[i - 1] way + 1 step
- climb i stairs can be dp[i - 2] way + 2 step
- So dp[i] = dp[i - 2] + dp[i - 1]


## Codes:
```TypeScript
function climbStairs(n: number): number {
    const dp: number[] = [1, 1]; 
    for (let i = 2; i <= n; i++) {
        dp[i] = dp[i - 2] + dp[i - 1];
    }
    return dp[n];
};
```

