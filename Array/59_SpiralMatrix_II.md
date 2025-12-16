# 59. Spiral Matrix II - Medium

## Problem Statement:
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

### Example 1:
![](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

**Input**: n = 3
**Output**: \[[1,2,3],[8,9,4],[7,6,5]]

### Example 2:

**Input**: n = 1
**Output**: \[[1]]

### Constraints:

- 1 <= n <= 20

## Solution Notes:
- set boundaries for left, right, up and down
- shrink one boundary each time to finish one loop until four boundaries become one point

## Codes:

- Python
``` Python
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        res = [[0] * n for _ in range(n)]
        l, r, u, d, count = 0, n - 1, 0, n - 1, 0
        while True:
            for i in range(l, r + 1): 
                count += 1
                res[u][i] = count
            u += 1
            if u > d: break

            for i in range(u, d + 1): 
                count += 1
                res[i][r] = count
            r -= 1
            if r < l: break

            for i in range(r, l - 1, -1): 
                count += 1
                res[d][i] = count
            d -= 1
            if d < u: break


            for i in range(d, u - 1, -1): 
                count += 1
                res[i][l] = count
            l += 1
            if l > r: break

        return res
```


- JavaScript
```JavaScript
/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function(n) {
    let res = new Array(n).fill(0).map(() => new Array(n).fill(0));
    let count = 0;
    let l = 0, r = n - 1, u = 0, d = n - 1;

    while (true) {
        for (let i = l; i <= r; i++) res[u][i] = ++count;
        if (++u > d) break;

        for (let i = u; i <= d; i++) res[i][r] = ++count;
        if (--r < l) break;

        for (let i = r; i >= l; i--) res[d][i] = ++count;
        if (--d < u) break;

        for (let i = d; i >= u; i--) res[i][l] = ++count;
        if (++l > r) break;
    }
    return res;
};
```

- Java

```Java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        
        int count = 0;

        while (true) {
            for (int i = left; i <= right; i++) res[up][i] = ++count;
            if (++up > down) break;

            for (int i = up; i <= down; i++) res[i][right] = ++count;
            if (--right < left) break;

            for (int i = right; i >= left; i--) res[down][i] = ++count;
            if (--down < up) break;

            for (int i = down; i >= up; i--) res[i][left] = ++count;
            if (++left > right) break;
        }

        return res;
    }
}
```