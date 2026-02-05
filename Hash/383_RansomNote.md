# 383. Ransom Note - Easy

## Problem Statement:
Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

### Example 1:

**Input:** ransomNote = "a", magazine = "b"
**Output:** false

### Example 2:

**Input:** ransomNote = "aa", magazine = "ab"
**Output:** false
Example 3:

**Input:** ransomNote = "aa", magazine = "aab"
**Output:** true
 
### Constraints:

- 1 <= ransomNote.length, magazine.length <= 105
- ransomNote and magazine consist of lowercase English letters.

## Solution Notes:
- Using array int[26] to store and takeout letters

## Codes:
```JavaScript
/**
 * @param {string} ransomNote
 * @param {string} magazine
 * @return {boolean}
 */
var canConstruct = function(ransomNote, magazine) {
    let arr = new Array(26).fill(0);
    for (const m of magazine) {
        arr[m.charCodeAt() - 'a'.charCodeAt()]++;
    }
    for (const r of ransomNote) {
        arr[r.charCodeAt() - 'a'.charCodeAt()]--;
        if (arr[r.charCodeAt() - 'a'.charCodeAt()] < 0) return false;
    }

    return true;
};
```

```Python
# Solution 1:
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        count = {}
        for m in magazine:
            count[m] = count.get(m, 0) + 1
        
        for r in ransomNote:
            if r not in count or count[r] == 0:
                return False
            count[r] -= 1
        
        return True

# Solution 2:
class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:
        count = [0] * 26
        for m in magazine:
            count[ord(m) - ord('a')] += 1
        
        for r in ransomNote:
            count[ord(r) - ord('a')] -= 1
            if count[ord(r) - ord('a')] < 0:
                return False
        
        return True
```

```Java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] array = new int[26];
        
        for (int i = 0; i < ransomNote.length(); i++) {
            array[ransomNote.charAt(i) - 'a']++;
        }

        for (int i = 0; i < magazine.length(); i++) {
            if (array[magazine.charAt(i) - 'a'] != 0) {
                array[magazine.charAt(i) - 'a']--;
            }
        }

        for (int i: array){
            if (i != 0) return false;
        }

        return true;
    }
}
```