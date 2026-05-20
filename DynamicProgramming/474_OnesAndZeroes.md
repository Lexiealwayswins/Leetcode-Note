# 474. Ones and Zeroes - Medium

## Problem Statement:  

You are given an array of binary strings strs and two integers m and n.

Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.

A set x is a subset of a set y if all elements of x are also elements of y.


### Example 1:  

**Input:** strs = ["10","0001","111001","1","0"], m = 5, n = 3
**Output:** 4
**Explanation:** The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
{"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.

### Example 2:  

**Input:** strs = ["10","0","1"], m = 1, n = 1
**Output:** 2
**Explanation:** The largest subset is {"0", "1"}, so the answer is 2.

### Constraints:

- 1 <= strs.length <= 600
- 1 <= strs[i].length <= 100
- strs[i] consists only of digits '0' and '1'.
- 1 <= m, n <= 100

## Solution Notes:  
- Dynamic Programming: Typical backpack problem 
- i is the max m, j is the max n, z is the 0 counts, o is the 1 counts
- dp[i][j] = Math.max(dp[i][j], dp[i - z][j - o] + 1);

## Codes:
```TypeScript
function findMaxForm(strs: string[], m: number, n: number): number {
    const dp: number[][] = Array.from({length: m + 1}, () => new Array(n + 1).fill(0));
    for (const s of strs) {
        let z = 0;
        let o = 0;
        for (const c of s) {
            if (c === "0") z++;
            if (c === "1") o++;
        }

        for (let i = m; i >= z; i--) {
            for (let j = n; j >= o; j--) {
                dp[i][j] = Math.max(dp[i][j], dp[i - z][j - o] + 1);
            }
        }
    }
    return dp[m][n];
};
```

