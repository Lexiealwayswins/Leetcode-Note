# 28. Find the Index of the First Occurrence in a String - Easy

## Problem Statement:  

Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

### Example 1:

**Input:** haystack = "sadbutsad", needle = "sad"
**Output:** 0
**Explanation:** "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.

### Example 2:

**Input:** haystack = "leetcode", needle = "leeto"
**Output:** -1
**Explanation:** "leeto" did not occur in "leetcode", so we return -1.
 

### Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.

## Solution Notes:
- use KMP Algorithm to get the prefix table, which is a array of the greatest dublicate prefix number.  
- ![](https://code-thinking.cdn.bcebos.com/pics/KMP精讲8.png)
- The reason why this approach is efficient: as the prefix is the same as suffix in the prefix table, when we meet a different character, we can skip matching the prefix and jump to the position which is after the prefix and before the suffix.


## Codes:

```Java
class Solution {
    public int strStr(String haystack, String needle) {

        if (needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i)) 
                j = next[j - 1];
            if (needle.charAt(j) == haystack.charAt(i)) 
                j++;
            if (j == needle.length()) 
                return i - needle.length() + 1;
        }
        return -1;

    }
    
    private void getNext(int[] next, String s) {
        int j = 0; // j is the end index of prefix
        next[0] = 0;
        // i is the end index of suffix
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i)) 
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i)) 
                j++;
            next[i] = j; 
        }
    }
}
```Java