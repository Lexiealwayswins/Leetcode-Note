# 242. Valid Anagram - Easy

## Problem Statement:
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

### Example 1:

**Input:**: s = "anagram", t = "nagaram"
**Output:**: true

### Example 2:

**Input:**: s = "rat", t = "car"
**Output:**: false

### Constraints:

- 1 <= s.length, t.length <= 5 * 104
- s and t consist of lowercase English letters.

## Solution Notes:
- When solving a containing questing, using hash to store and takeout
- check whether the left container is empty, if yes, return true, otherwise return false
- characters can use the int[26] array to store

## Codes:
- Python
```Python
# Solution 1:
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        from collections import Counter
        s_count = Counter(s)
        t_count = Counter(t)
        return s_count == t_count

# Solution 2:
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        from collections import defaultdict
        s_int = defaultdict(int)
        t_int = defaultdict(int)

        for i in s:
            s_int[i] += 1
        for i in t:
            t_int[i] += 1

        return s_int == t_int

# Solution 3:
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        arr = [0] * 26
        for i in s:
            arr[ord(i) - ord('a')] += 1
           
        for i in t:
            arr[ord(i) - ord('a')] -= 1
        
        for i in range(26):
            if arr[i] != 0:
                return False

        return True

# Solution 4:
class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t): return False
        arr = {}
        for i in range(len(s)):
            if s[i] in arr:
                arr[s[i]] += 1
            else:
                arr[s[i]] = 1

        for j in range(len(t)):
            if t[j] in arr:
                arr[t[j]] -= 1
                if arr[t[j]] == 0:
                    del arr[t[j]]
            else:
                return False

        if arr:
            return False
        else:
            return True  
```

- JavaScript
```JavaScript
// Solution 1:
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    if (s.length != t.length) return false;
    let arr = new Array(26).fill(0);
    for (const i of s) {
        arr[i.charCodeAt() - 'a'.charCodeAt()]++;
    }
    for (const i of t) {
        arr[i.charCodeAt() - 'a'.charCodeAt()]--;
    }
    for (const i of arr) {
        if (i !== 0) return false;;
    }
    return true;
};

// Solution 2:
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    if (s.length != t.length) return false;
    let map = new Map();
    for (const i of s) {
        map.set(i, (map.get(i) || 0) + 1);
    }
    for (const i of t) {
        if (!map.get(i)) return false;
        map.set(i, map.get(i) - 1);
    }

    return true;
};
```

- Java
```Java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] list = new int[26];

        for (int i=0; i<s.length(); i++) {
            list[s.charAt(i) - 'a']++;
        }

        for (int i=0; i<t.length(); i++) {
            list[t.charAt(i) - 'a']--;
        }

        for (int c : list){
            if (c != 0) return false;
        }

        return true;
    }
}
```