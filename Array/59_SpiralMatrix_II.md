# 59. Spiral Matrix II - Medium

## Problem Statement:
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

### Example 1:
![](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

**Input**: n = 3
**Output**: [[1,2,3],[8,9,4],[7,6,5]]
**Example** 2:

**Input**: n = 1
**Output**: [[1]]

### Constraints:

- 1 <= n <= 20

## Solution Notes:
- set boundaries for left, right, up and down
- shrink one boundary each time to finish one loop until four boundaries become one point

## Codes:

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