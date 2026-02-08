# 150. Evaluate Reverse Polish Notation - Medium

## Problem Statement:

You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

### Example 1:

**Input:** tokens = ["2","1","+","3","*"]  
**Output:** 9  
**Explanation:** ((2 + 1) * 3) = 9  

### Example 2:

**Input:** tokens = ["4","13","5","/","+"]  
**Output:** 6  
**Explanation:** (4 + (13 / 5)) = 6  

### Example 3:

**Input:** tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]  
**Output:** 22  
**Explanation:** ((10 * (6 / ((9 + 3) * -11))) + 17) +   
= ((10 * (6 / (12 * -11))) + 17) + 5  
= ((10 * (6 / -132)) + 17) + 5  
= ((10 * 0) + 17) + 5  
= (0 + 17) + 5  
= 17 + 5  
= 22  
 

### Constraints:

- 1 <= tokens.length <= 104  
- tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].  

## Solution Notes:
- Use Stack

## Codes:
```Python
from operator import add, sub, mul
class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        dic = {
            '+': add,
            '-': sub,
            '*': mul,
            '/': self.div
        }
        for i in tokens:
            if i in dic:
                b = stack.pop()
                a = stack.pop()
                stack.append(dic[i](int(a), int(b)))
            else:
                stack.append(int(i))

        return stack.pop()

    def div (self, a, b):
        return int(a / b) if a * b > 0 else -(abs(a) // abs(b))

```

```JavaScript
/**
 * @param {string[]} tokens
 * @return {number}
 */
var evalRPN = function(tokens) {
    let stack = [];
    for (const i of tokens) {
        if (isNaN(Number(i))) {
            const b = stack.pop();
            const a = stack.pop();
            if (i === '+') {
                stack.push(a + b);
            } else if (i === '-') {
                stack.push(a - b);
            } else if (i === '*') {
                stack.push(a * b);
            } else if (i === '/') {
                stack.push(a / b | 0); // | 0是向0取整的位运算，2.9 | 0的结果是 2， -2.9 | 0 的结果是 -2
            }
        } else {
            stack.push(Number(i));
        }
    }
    return stack.pop();
};
```

```Java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s: tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(- stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }
}
```Java
