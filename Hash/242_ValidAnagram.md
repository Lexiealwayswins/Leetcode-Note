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