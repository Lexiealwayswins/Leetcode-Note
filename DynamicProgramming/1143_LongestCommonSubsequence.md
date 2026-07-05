# 1143. Longest Common Subsequence - medium

## Problem Statement:  

Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.


### Example 1:

**Input:** text1 = "abcde", text2 = "ace" 
**Output:** 3  
**Explanation:** The longest common subsequence is "ace" and its length is 3.

### Example 2:

**Input:** text1 = "abc", text2 = "abc"
**Output:** 3
**Explanation:** The longest common subsequence is "abc" and its length is 3.

### Example 3:

**Input:** text1 = "abc", text2 = "def"
**Output:** 0
**Explanation:** There is no such common subsequence, so the result is 0.
 

### Constraints:

- 1 <= text1.length, text2.length <= 1000
- text1 and text2 consist of only lowercase English characters.


## Solution Notes:  
- Dynamic Programming
- similar to 718
- careful about the initialization

## Codes:
```TypeScript
// Initialization
function longestCommonSubsequence(text1: string, text2: string): number {
    const dp: number[][] = Array.from({length: text1.length}, () => new Array(text2.length).fill(0));
    for (let i = 0; i < text1.length; i++) {
        if (text1[i] === text2[0]) {
            while (i < text1.length) {
                dp[i][0] = 1;
                i++;
            }
            break;
        }
    }
    for (let j = 0; j < text2.length; j++) {
        if (text1[0] === text2[j]) {
            while (j < text2.length) {
                dp[0][j] = 1;
                j++;
            }
            break;
        }
    }

    let res = 0;
    for (let i = 0; i < text1.length; i++) {
        for (let j = 0; j < text2.length; j++) { 
            if (i > 0 && j > 0) {
                if (text1[i] === text2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            res = Math.max(res, dp[i][j]);
        }
    }

    return res;
};

// No Initialization
function longestCommonSubsequence(text1: string, text2: string): number {
    const dp: number[][] = Array.from({length: text1.length + 1}, () => new Array(text2.length + 1).fill(0));
    for (let i = 1; i <= text1.length; i++) {
        for (let j = 1; j <= text2.length; j++) { 
            if (text1[i - 1] === text2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    return dp[text1.length][text2.length];
};
```

