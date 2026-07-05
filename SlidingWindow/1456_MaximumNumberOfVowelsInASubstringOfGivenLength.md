# 1456. Maximum Number of Vowels in a Substring of Given Length - Medium

## Problem Statement:

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.


### Example 1:

**Input**: s = "abciiidef", k = 3
**Output**: 3
**Explanation**: The substring "iii" contains 3 vowel letters.

### Example 2:

**Input**: s = "aeiou", k = 2
**Output**: 2
**Explanation**: Any substring of length 2 contains 2 vowels.

### Example 3:

**Input**: s = "leetcode", k = 3
**Output**: 2
**Explanation**: "lee", "eet" and "ode" contain 2 vowels.
 

### Constraints:

- 1 <= s.length <= 105
- s consists of lowercase English letters.
- 1 <= k <= s.length

## Solution Notes:
- Two Pointers approach
- Sliding windows

## Codes:

```TypeScript
function maxVowels(s: string, k: number): number {
    const v: Set<string> = new Set(['a', 'e', 'i', 'o', 'u']);

    let i = 0, j = 0;
    let max = 0, count = 0;
    while (j < k) {
        if (v.has(s[j++])) count++;
    }
    max = Math.max(max, count);
    while (j < s.length) {
        if (v.has(s[i++])) count--;
        if (v.has(s[j++])) count++;
        max = Math.max(max, count);
    }

    return max;
};
```