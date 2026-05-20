# 394. Decode String - Medium

## Problem Statement:

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.


### Example 1:

**Input:** s = "3[a]2[bc]"
**Output:** "aaabcbc"

### Example 2:

**Input:** s = "3[a2[c]]"
**Output:** "accaccacc"

### Example 3:

**Input:** s = "2[abc]3[cd]ef"
**Output:** "abcabccdcdcdef"

### Constraints:

- 1 <= s.length <= 30
- s consists of lowercase English letters, digits, and square brackets '[]'.
- s is guaranteed to be a valid input.
- All the integers in s are in the range [1, 300].

## Solution Notes:
- Use stack to store results, not storing traversal characters

## Codes:
```TypeScript
function decodeString(s: string): string {
    const stack: Array<string | number> = [];
    let res = "";
    let n: number = 0;

    for (const c of s) {
        if (c === "[") {
            stack.push(res);
            stack.push(Number(n));
            n = 0;
            res = "";
        } else if (c === "]") {
            const num = stack.pop() as number;
            const str = stack.pop() as string;
            res = str + res.repeat(num);
        } else if (/\d/.test(c)){
            n = 10 * n + Number(c);
        } else {
            res += c;
        }
    }
    return res;
};
```