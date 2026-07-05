# 583. Delete Operation for Two Strings - Medium

## Problem Statement:  

Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

### Example 1:

**Input:** word1 = "sea", word2 = "eat"
**Output:** 2
**Explanation:** You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

### Example 2:

**Input:** word1 = "leetcode", word2 = "etco"
**Output:** 4

### Constraints:

- 1 <= word1.length, word2.length <= 500
- word1 and word2 consist of only lowercase English letters.


## Solution Notes:  
- Dynamic Programming - similar to problem 115
- Solution 1: get the same sequence length of word1 and word2，using the length of word1 and word2 minus the common part, the rest is the result
- Solution 2: dp[i][j]定义为以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。

## Codes:
```Python
# Solution 1:
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp = [[0] *  (len(word2) + 1) for _ in range(len(word1) + 1)]

        for i in range(1, len(word1) + 1): 
            for j in range(1, len(word2) + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + 1
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

        return len(word1) + len(word2) - (dp[-1][-1] * 2)

# Solution 2:
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        dp = [[0] *  (len(word2) + 1) for _ in range(len(word1) + 1)]
        for i in range(len(word1) + 1): dp[i][0] = i
        for j in range(len(word2) + 1): dp[0][j] = j

        for i in range(1, len(word1) + 1): 
            for j in range(1, len(word2) + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    # 情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
                    # 情况二：删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
                    # 情况三：同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
                    # 因为 dp[i][j - 1] + 1 = dp[i - 1][j - 1] + 2
                    # 从字面上理解 就是 当 同时删word1[i - 1]和word2[j - 1]，dp[i][j-1] 本来就不考虑 word2[j - 1]了，那么我在删 word1[i - 1]，是不是就达到两个元素都删除的效果，即 dp[i][j-1] + 1。
                    # 所以递推公式可简化为：dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)

        return dp[-1][-1]
```

