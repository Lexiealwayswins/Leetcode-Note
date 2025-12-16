# 54. Spiral Matrix - Medium

## Problem Statement:
Given an m x n matrix, return all elements of the matrix in spiral order.

### Example 1:
![](https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg)

**Input**: matrix = \[[1,2,3],[4,5,6],[7,8,9]]
**Output**: [1,2,3,6,9,8,7,4,5]

### Example 2:
![](https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg)

**Input**: matrix = \[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
**Output**: [1,2,3,4,8,12,11,10,9,5,6,7]

### Constraints:
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 10
- -100 <= matrix[i][j] <= 100

## Solution Notes:
- set boundaries for left, right, up and down
- shrink one boundary each time to finish one loop until four boundaries become one point

## Codes:

- Python
``` Python
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        l, r, u, d, count = 0, len(matrix[0]) - 1, 0, len(matrix) - 1, 0

        while True:
            for i in range(l, r + 1): 
                res.append(matrix[u][i])
            u += 1
            if u > d: break

            for i in range(u, d + 1): 
                res.append(matrix[i][r])
            r -= 1
            if r < l: break

            for i in range(r, l - 1, -1): 
                res.append(matrix[d][i])
            d -= 1
            if d < u: break

            for i in range(d, u - 1, -1): 
                res.append(matrix[i][l])
            l += 1
            if l > r: break

        return res
```


- JavaScript
```JavaScript
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function(matrix) {
    let res = []
    let l = 0, r = matrix[0].length - 1, u = 0, d = matrix.length - 1;

    while (true) {
        for (let i = l; i <= r; i++) res.push(matrix[u][i]);
        if (++u > d) break;

        for (let i = u; i <= d; i++) res.push(matrix[i][r]);
        if (--r < l) break;

        for (let i = r; i >= l; i--) res.push(matrix[d][i]);
        if (--d < u) break;

        for (let i = d; i >= u; i--) res.push(matrix[i][l]);
        if (++l > r) break;
    }
    return res;
};
```

- Java

```Java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // clarify boundaries
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        ArrayList<Integer> res = new ArrayList<>();

        while (true) {
            for (int i = left; i <= right; i++) res.add(matrix[up][i]);
            if (++up > down) break;

            for (int i = up; i <= down; i++) res.add(matrix[i][right]);
            if (--right < left) break;

            for (int i = right; i >= left; i--) res.add(matrix[down][i]);
            if (--down < up) break;

            for (int i = down; i >= up; i--) res.add(matrix[i][left]);
            if (++left > right) break;
        }

        return res;
    }
}
```