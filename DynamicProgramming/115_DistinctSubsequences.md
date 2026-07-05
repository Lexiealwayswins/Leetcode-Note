# 115. Distinct Subsequences - Hard

## Problem Statement:  

Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.
 

### Example 1:

**Input:** s = "rabbbit", t = "rabbit"
**Output:** 3
**Explanation:**
As shown below, there are 3 ways you can generate "rabbit" from s.
**rabb**b**it**
**ra**b**bbit**
**rab**b**bit**

### Example 2:

**Input:** s = "babgbag", t = "bag"
**Output:** 5

### **Explanation:**
As shown below, there are 5 ways you can generate "bag" from s.
**ba**b**g**bag
**ba**bgba**g**
**b**abgb**ag**
ba**b**gb**ag**
babg**bag**
 

### Constraints:

- 1 <= s.length, t.length <= 1000
- s and t consist of English letters.


## Solution Notes:  
- Dynamic Programming
- 定义 dp[i][j] 为：s 的前 i 个字符中，能够组成 t 的前 j 个字符的子序列个数。

## Codes:
```TypeScript
function numDistinct(s: string, t: string): number {
    // 定义 dp[i][j] 为：s 的前 i 个字符中，能够组成 t 的前 j 个字符的子序列个数。
    const dp: number[][] = Array.from({length: s.length + 1}, () => new Array(t.length + 1).fill(0));
    // dp[i][0] 代表的含义是：用 s 的前 i 个字符去凑出一个“空字符串 t”有多少种方法。
    // 如果你要凑出一个“空集”，方案只有 1 种——那就是什么字符都不选（删除 s 的所有字符）。
    for (let i = 0; i <= s.length; i++) dp[i][0] = 1;
    // dp[0][j] 代表用“空字符串 s”去凑出一个非空的 t，这显然是不可能的，所以方案数是 0
    for (let j = 1; j <= t.length; j++) dp[0][j] = 0;

    for (let i = 1; i <= s.length; i++) {
        for (let j = 1; j <= t.length; j++) {
            if (s[i - 1] === t[j - 1]) {
                // 策略 A：不使用 s[i-1] 参与匹配
                // 你完全依靠之前的 s 前缀凑齐 t 的前 j 个字符（即 dp[i-1][j]）
                // 策略 B：强制使用 s[i-1] 参与匹配
                // 你需要从之前的 s 中凑齐 t 的前 j-1 个字符（即 dp[i-1][j-1]）,因为要用第i和第j个字符
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            } else {
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
    return dp[s.length][t.length];
};
```

