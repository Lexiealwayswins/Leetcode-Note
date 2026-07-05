# 3. Longest Substring Without Repeating Characters - Medium

## Problem Statement:
Given a string s, find the length of the longest substring without duplicate characters.

### Example 1:

**Input:** s = "abcabcbb"
**Output:** 3
**Explanation:** The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

### Example 2:

**Input:** s = "bbbbb"
**Output:** 1
**Explanation:** The answer is "b", with the length of 1.

### Example 3:

**Input:** s = "pwwkew"
**Output:** 3
**Explanation:** The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 
### Constraints:

- 0 <= s.length <= 5 * 104
- s consists of English letters, digits, symbols and spaces.

## Solution Notes:
- Sliding Window
- Maintain the window by add new and delete passed items, then record the window size

## Codes:
```TypeScript
function lengthOfLongestSubstring(s: string): number {
    const map = new Map<string, number>();
    let res: number = 0;
    let slow: number = 0, fast:number = 0;
    while (fast < s.length) {
        if (map.has(s[fast])) {
            while (slow <= map.get(s[fast])) {
                map.delete(s[slow]);
                slow++;
            }
        } 
        map.set(s[fast], fast);
        res = Math.max(res, fast - slow + 1);
        fast++;
    }
    return res;
};
```

```Python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if len(s) == 0: return 0
        l, r = 0, 0
        res = 1
        temp = set()
        while r < len(s):
            while s[r] in temp:
                temp.remove(s[l])
                l += 1
            temp.add(s[r])
            res = max(res, r - l + 1)
            r += 1
        return res

```

```Java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int l = 0, r = 0;
        Set<Character> set = new HashSet<>();

        while (r < s.length()) {
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(s.charAt(r));
            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
}
```