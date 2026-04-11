# 738. Monotone Increasing Digits - Medium

## Problem Statement:  

An integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.

Given an integer n, return the largest number that is less than or equal to n with monotone increasing digits.


### Example 1:  

**Input:**: n = 10
**Output:**: 9

### Example 2:  

**Input:**: n = 1234
**Output:**: 1234
 

### Constraints:

**Input:**: n = 332
**Output:**: 299

## Solution Notes:  
- Greedy Algorithm: traversal from the back

## Codes:
```TypeScript
// Solution 1:
function monotoneIncreasingDigits(n: number): number {
    const arr = Array.from(String(n), Number);
    if (arr.length === 1) return n;
    for (let i = arr.length - 2; i >= 0; i--) {
        if (arr[i] > arr[i + 1]) {
            arr[i] -= 1;
            let j = i + 1;
            while (j < arr.length) {
                arr[j] = 9;
                j++;
            }
        }
    }

    return Number(arr.map(i => String(i)).join(""));
};

// Solution 2:
function monotoneIncreasingDigits(n: number): number {
    const arr: number[] = Array.from(String(n), Number);
    if (arr.length === 1) return n;
    let flag = arr.length;
    for (let i = arr.length - 2; i >= 0; i--) {
        if (arr[i] > arr[i + 1]) {
            arr[i] -= 1;
            flag = i + 1;
        }
    }

    for (let i = flag; i < arr.length; i++) {
        arr[i] = 9;
    }

    return Number(arr.map(i => String(i)).join(""));
};

```

