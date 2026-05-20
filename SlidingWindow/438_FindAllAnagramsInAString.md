# 438. Find All Anagrams in a String - Medium

## Problem Statement:
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

### Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

### Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

 
### Constraints:

- 1 <= s.length, p.length <= 3 * 104
- s and p consist of lowercase English letters.

## Solution Notes:
- Sliding Window
- Maintain the window by add new and delete passed items

## Codes:

```Java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> map_p = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map_p.put(p.charAt(i), map_p.getOrDefault(p.charAt(i), 0) + 1);
        }

        int l = 0, r = 0, match = 0;
        Map<Character, Integer> map_s = new HashMap<>();

        while (r < s.length()) {
            char c = s.charAt(r);
            if (map_p.containsKey(c)) {
                map_s.put(c, map_s.getOrDefault(c, 0) + 1);
                // == compares reference，equals() compares numbers
                // when number is bigger than 127，== can be false，while equals() is still true
                // if (map_s.get(c) == map_p.get(c)) match++;
                if (map_s.get(c).equals(map_p.get(c))) match++;
            }

            while (match == map_p.size()) {
                if (r - l + 1 == p.length()) res.add(l);

                char c_l = s.charAt(l);
                if (map_p.containsKey(c_l)) {
                    map_s.put(c_l, map_s.get(c_l) - 1);
                    if (map_s.get(c_l) < map_p.get(c_l)) match--;
                }

                l++;
            }
            r++;
        }
        return res;
    }
}
```