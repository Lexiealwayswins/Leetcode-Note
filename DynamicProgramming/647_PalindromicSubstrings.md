# 647. Palindromic Substrings - Medium

## Problem Statement:  

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.


### Example 1:

**Input:**: s = "abc"
**Output:**: 3
**Explanation:**: Three palindromic strings: "a", "b", "c".

### Example 2:

**Input:**: s = "aaa"
**Output:**: 6
**Explanation:**: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 
### Constraints:

- 1 <= s.length <= 1000
- s consists of lowercase English letters.

## Solution Notes:  
- Dynamic Programming
- 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。


## Codes:
```Java
class Solution {
    public int countSubstrings(String s) {
        // 默认初始化都是false
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        // 因为dp[i][j]是根据dp[i + 1][j - 1]推出来的，所以i必须从下到上遍历，j必须从左到右遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            // j从i开始因为i一定是左边区间起始点，dp[i][j]：表示区间范围[i,j]
            for (int j = i; j < s.length(); j++) {
                // 整体上是两种，就是s[i]与s[j]相等，s[i]与s[j]不相等这两种, 不相等默认false
                if (s.charAt(i) == s.charAt(j)) {
                    // 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
                    // 情况二：下标i 与 j相差为1，例如aa，也是回文子串
                    if (j - i <= 1) {
                        res++;
                        dp[i][j] = true;
                    // 情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看i到j区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 i+1 与 j-1区间，这个区间是不是回文就看dp[i + 1][j - 1]是否为true。
                    } else if (dp[i + 1][j - 1]){
                        res++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return res;
    }
}
```

