# 844. Backspace String Compare - Easy

## Problem Statement:

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.
 
### Example 1:

**Input**: s = "ab#c", t = "ad#c"
**Output**: true
**Explanation**: Both s and t become "ac".

### Example 2:

**Input**: s = "ab##", t = "c#d#"
**Output**: true
**Explanation**: Both s and t become "".

### Example 3:

**Input**: s = "a#c", t = "b"
**Output**: false
**Explanation**: s becomes "c" while t becomes "b".
 

### Constraints:

- 1 <= s.length, t.length <= 200
- s and t only contain lowercase letters and '#' characters.
 
### Follow up: Can you solve it in O(n) time and O(1) space?

## Solution Notes:
- Using Stack - O(n) time and O(n) space
- Two Pointers approach for two strings - O(n) time and O(1) space

## Codes:

### Solution 1 - O(n) time and O(n) space
- Python
```Python
class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        return self.backspace(s) == self.backspace(t)

    def backspace(self, s: str) -> str:
        r = []
        for i in range(len(s)):
            r.append(s[i])
            if s[i] == "#":
                r.pop()
                if r:
                    r.pop()
        return "".join(r)
```

- JavaScript
```JavaScript
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var backspaceCompare = function(s, t) {
    return backspace(s) == backspace(t);
};

function backspace (s) {
    let r = []
    for (let i = 0; i < s.length; i++) {
        r.push(s[i]);
        if (s[i] == "#") {
            r.pop();
            if (r) {
                r.pop();
            }
        }
    }
    return r.join("");
}
```

- Java
```Java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        if (backspaceCharacter(s).equals(backspaceCharacter(t))) {
            return true;
        } else {
            return false;
        }
    }

    public static Stack backspaceCharacter(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        return stack;
    }
}
```

### Solution 2
- Python
```Python
class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        i, j = len(s) - 1, len(t) - 1
        skipS, skipT = 0, 0

        while i >= 0 or j >= 0:
            while i >= 0:
                if s[i] == "#":
                    skipS += 1
                    i -= 1
                elif skipS > 0:
                    i -= 1
                    skipS -= 1
                else:
                    break
            
            while j >= 0:
                if t[j] == "#":
                    skipT += 1
                    j -= 1
                elif skipT > 0:
                    j -= 1
                    skipT -= 1
                else:
                    break
            
            if i >= 0 and j >= 0 and s[i] != t[j]:
                return False
            if (i >= 0) != (j >= 0):
                return False

            i -= 1
            j -= 1

        return True
```

- JavaScript
```JavaScript
/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var backspaceCompare = function(s, t) {
    let i = s.length - 1, j = t.length - 1, skipS = 0, skipT = 0;
    while (i >= 0 || j >= 0) {
        while (i >= 0) {
            if (s[i] == "#") {
                i--;
                skipS++;
            } else if (skipS > 0) {
                i--;
                skipS--;
            } else {
                break;
            }
        }
        while (j >= 0) {
            if (t[j] == "#") {
                j--;
                skipT++;
            } else if (skipT > 0) {
                j--;
                skipT--;
            } else {
                break;
            }
        }
        if (i >= 0 && j >= 0 && s[i] != t[j]) return false;
        if ((i >= 0) != (j >= 0)) return false;

        i--;
        j--;
    }
    return true;
};
```