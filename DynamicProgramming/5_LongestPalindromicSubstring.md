# 5. Longest Palindromic Substring - Medium

## Problem Statement:  

Given a string s, return the longest palindromic substring in s.

### Example 1:

**Input**: s = "babad"
**Output**: "bab"
**Explanation**: "aba" is also a valid answer.

### Example 2:

**Input**: s = "cbbd"
**Output**: "bb"

### Constraints:

- 1 <= s.length <= 1000
- s consist of only digits and English letters.

## Solution Notes:  
- Dynamic Programming - Similar to problem 647
- 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。


## Codes:
```Java
// 精简版：
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > res.length()) {
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}

// 详细逻辑版：
class Solution {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        if (j - i + 1 > res.length()) {
                            res = s.substring(i, j + 1);
                        }

                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (j - i + 1 > res.length()) {
                            res = s.substring(i, j + 1);
                        }
                    }
                }
            }
        }
        return res;
    }
}
```

