# 72. Edit Distance - Medium

## Problem Statement:  

Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

- Insert a character
- Delete a character
- Replace a character

### Example 1:

**Input:** word1 = "horse", word2 = "ros"
**Output:** 3
**Explanation:** 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

### Example 2:

**Input:** word1 = "intention", word2 = "execution"
**Output:** 5
**Explanation:** 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 
### Constraints:

- 0 <= word1.length, word2.length <= 500
- word1 and word2 consist of lowercase English letters.


## Solution Notes:  
- Dynamic Programming - similar to problem 583
- dp[i][j]定义为以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要编辑元素的最少次数。

## Codes:
```Java
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 相等就不操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 操作一：word1删除一个元素，那么就是word1的前i-1个元素与word2的前j个元素的最近编辑距离 再加上一个操作，即 dp[i][j] = dp[i - 1][j] + 1;
                    // 操作二：word2删除一个元素，那么就是word1的前i个元素与word2的前j-1个元素的最近编辑距离 再加上一个操作，即 dp[i][j] = dp[i][j - 1] + 1;
                    // word2添加一个元素，相当于word1删除一个元素，例如 word1 = "ad" ，word2 = "a"，word1删除元素'd' 和 word2添加一个元素'd'，变成word1="a", word2="ad"， 最终的操作数是一样！
                    // 操作三：替换元素，word1替换word1[i - 1]，使其与word2[j - 1]相同，此时不用增删加元素。 因为 if (word1[i - 1] == word2[j - 1])的时候我们的操作 是 dp[i][j] = dp[i - 1][j - 1] ，那么只需要一次替换的操作，就可以让 word1[i - 1] 和 word2[j - 1] 相同。所以 dp[i][j] = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
```

