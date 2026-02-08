# 20. Valid Parentheses - Easy

## Problem Statement:
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

### Example 1:  

**Input:** s = "()"  
**Output:** true  

### Example 2:

**Input:** s = "()[]{}"  
**Output:** true  

### Example 3:  

**Input:** s = "(]"  
**Output:** false  
 

### Constraints:

- 1 <= s.length <= 104
- s consists of parentheses only '()[]{}'.

## Solution Notes:
- Use a stack to store and delete pairs
- If the stack is empty in the end, it should return true

## Codes:
```Python
class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        map = {
            '(': ')',
            '[': ']',
            '{': '}'
        }

        for i in s:
            if i in map.keys():
                stack.append(map[i])
            elif not stack or stack[-1] != i:
                return False
            else:
                stack.pop()
        return not len(stack)
```

```JavaScript
/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function(s) {
    let arr = [];
    for (const c of s) {
        if (c === '(') {
            arr.push(')');
        } else if (c === '[') {
            arr.push(']');
        } else if (c === '{') {
            arr.push('}');
        } else if (!arr.length || arr[arr.length - 1] !== c) {
            return false;
        } else {
            arr.pop();
        }
    }
    return !arr.length;
};
```

```Java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                stack.push(')');
            } else if (s.charAt(i) == '[') {
                stack.push(']');
            } else if (s.charAt(i) == '{'){
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                return false;
            } else {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
```Java