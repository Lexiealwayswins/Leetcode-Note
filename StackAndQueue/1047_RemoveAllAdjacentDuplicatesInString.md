# 1047. Remove All Adjacent Duplicates In String - Easy

## Problem Statement:

You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

 
### Example 1:

**Input:** s = "abbaca"  
**Output:** "ca"  
**Explanation: **
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".  

### Example 2:

**Input:** s = "azxxzy"  
**Output:** "ay"  
 

### Constraints:

- 1 <= s.length <= 105
- s consists of lowercase English letters.

## Solution Notes:
- Similar to question 20
- Use a String to realize stack so that we do not need to transfer from stack to string 

## Codes:
```Python
# Two pointer approach
class Solution:
    def removeDuplicates(self, s: str) -> str:
        res = list(s)
        l, r = 0, 0
        while r < len(s):
            res[l] = res[r]
            if l > 0 and res[l] == res[l - 1]:
                l -= 1
            else:
                l += 1

            r += 1
        return "".join(res[0 : l])
```


```JavaScript
/**
 * @param {string} s
 * @return {string}
 */
var removeDuplicates = function(s) {
    let stack = [];
    for (const c of s) {
        if (stack.length > 0 && stack[stack.length - 1] === c) {
            stack.pop();
        } else {
            stack.push(c);
        }
    }
    return stack.join('');
};
```

```Java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder res = new StringBuilder();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (top >= 0 && res.charAt(top) == s.charAt(i)) {
                res.deleteCharAt(top);
                top--;
            } else {
                res.append(s.charAt(i));
                top++;
            }
        }
        return res.toString();
    }
}
```Jave