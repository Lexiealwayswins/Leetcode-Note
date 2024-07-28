# 459. Repeated Substring Pattern - Easy

## Problem Statement:  

Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

 

### Example 1:

**Input:** s = "abab"  
**Output:** true  
**Explanation:** It is the substring "ab" twice.  

### Example 2:

**Input:** s = "aba"   
**Output:** false  

### Example 3:

**Input:** s = "abcabcabcabc"  
**Output:** true  
**Explanation:** It is the substring "abc" four times or the substring "abcabc" twice.
 

### Constraints:
- 1 <= s.length <= 104
- s consists of lowercase English letters.

## Solution Notes:
- use KMP Algorithm to get the prefix table, which is a array of the greatest dublicate prefix number.
- ![](https://img-blog.csdnimg.cn/img_convert/5bfe14f5823fef0b9556f0406b2a8c00.png)

## Codes:

```Java
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int[] next = new int[s.length()];

        for (int i = 1, j = 0; i < s.length(); i++) {
            next[0] = 0;
            while(j > 0 && s.charAt(i) != s.charAt(j)) j = next[j - 1];
            if (s.charAt(i) == s.charAt(j)) j++;
            next[i] = j;
        }
        if (next[s.length() - 1] > 0 && s.length() % (s.length() - next[s.length() - 1]) == 0) return true;
        return false;
    }
}
```Java