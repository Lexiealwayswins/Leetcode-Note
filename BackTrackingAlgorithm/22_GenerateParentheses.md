# 22. Generate Parentheses - Medium

## Problem Statement:  

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

### Example 1:  

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

### Example 2:  

Input: n = 1
Output: ["()"]
 
### Constraints:  

- 1 <= n <= 8

## Solution Notes:   
- Backtracking
- o is number of "(", c is number of ")"
- Always match o to n so that the total number are right
- Always match c to o so that they are always a pair
- match o firstly then match c to make sure they are in order

## Codes:  
```TypeScript
function generateParenthesis(n: number): string[] {
    const res: string[] = [];
    let path: string = "";

    // o is number of "(", c is number of ")"
    function backtracking (o: number, c: number, n: number) : void {
        if (o === n && c === n) {
            res.push(path);
            return;
        }

        // c
        if (o < n) {
            path += "(";
            backtracking(o + 1, c, n);
            path = path.slice(0, -1);
        }

        if (c < o) {
            path += ")";
            backtracking(o, c + 1, n);
            path = path.slice(0, -1);
        }
        return;
    }
    backtracking(0, 0, n);

    return res;
};
```