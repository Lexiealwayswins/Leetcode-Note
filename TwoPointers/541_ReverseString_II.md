# 541. Reverse String II - Easy

## Problem Statement:
Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

### Example 1:

**Input:** s = "abcdefg", k = 2
**Output:** "bacdfeg"

### Example 2:

**Input:** s = "abcd", k = 2
**Output:** "bacd"

### Constraints:

- 1 <= s.length <= 104
- s consists of only lowercase English letters.
- 1 <= k <= 104

## Solution Notes:
- Using a loop gapped by 2*k
- clarrify the pointer of the right because if there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

## Codes:
```Java
class Solution {
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();

        for (int i = 0; i < a.length; i += 2 * k) {
            int l = i;
            int r = a.length - 1 > i + k - 1 ? i + k - 1 : a.length - 1;

            while (l < r) {
                a[l] ^= a[r];
                a[r] ^= a[l];
                a[l] ^= a[r];
                l++;
                r--;
            }
        }
        return new String(a);
    }
}
```