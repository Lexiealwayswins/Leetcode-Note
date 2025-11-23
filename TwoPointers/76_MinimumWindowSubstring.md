# 76. Minimum Window Substring - Hard

## Problem Statement:

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

### Example 1:

**Input**: s = "ADOBECODEBANC", t = "ABC"
**Output**: "BANC"
**Explanation**: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

### Example 2:

**Input**: s = "a", t = "a"
**Output**: "a"
**Explanation**: The entire string s is the minimum window.

### Example 3:

**Input**: s = "a", t = "aa"
**Output**: ""
**Explanation**: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

### Constraints:

- m == s.length
- n == t.length
- 1 <= m, n <= 105
- s and t consist of uppercase and lowercase English letters.
 

### Follow up: Could you find an algorithm that runs in O(m + n) time?

```Python
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        slow, fast, count, min_len = 0, 0, 0, float('inf')
        res = ''
        t_map = collections.Counter(t)

        for fast, c in enumerate(s):
            if c in t_map:
                t_map[c] -= 1
                if t_map[c] >= 0:
                    count += 1
            
            while count == len(t):
                if min_len > fast - slow + 1:
                    min_len = fast - slow + 1
                    res = s[slow : fast + 1]
                if s[slow] in t_map:
                    t_map[s[slow]] += 1
                    if t_map[s[slow]] > 0:
                        count -= 1
                slow += 1

        return res
        
```