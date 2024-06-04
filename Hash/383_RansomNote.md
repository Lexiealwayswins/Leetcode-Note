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